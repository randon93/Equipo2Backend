package com.ceiba.laboratorio.models.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import com.ceiba.laboratorio.converter.PrestamoMapper;
import com.ceiba.laboratorio.models.domain.PrestamoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ceiba.laboratorio.commonUtils.calendar.UtilCalendar;
import com.ceiba.laboratorio.commonUtils.palindromo.UtilPalindromic;
import com.ceiba.laboratorio.commonUtils.prestamos.UtilsFechaEntrega;
import com.ceiba.laboratorio.converter.LibroMapper;
import com.ceiba.laboratorio.models.dao.LibroDao;
import com.ceiba.laboratorio.models.dao.PrestamoDao;
import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.PrestamoSolicitudDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.entity.LibroEntity;
import com.ceiba.laboratorio.models.entity.PrestamoEntity;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import com.ceiba.laboratorio.models.service.LibroService;
import com.ceiba.laboratorio.models.service.UsuarioService;

@Service
public class LibroServiceImpl implements LibroService {

	private static final String OSERVACION_DEFAULT = "LIBRO PRESTADO EN PREFECTAS CONDICIONES";

	@Autowired
	private LibroMapper libroMapper;

	@Autowired
	private PrestamoMapper prestamoMapper;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LibroDao libroDao;

	@Autowired
	private PrestamoDao prestaDao;

	@Override
	public RespuestaDomain guardarLibro(LibroDomain libroDomain) {

		LibroEntity libroEntity = libroDao.findByIsbn(libroDomain.getIsbn());
		if (Objects.nonNull(libroEntity)) {
			Integer total = libroEntity.getCantidadTotal();
			Integer disponibles = libroEntity.getCantidadDisponible();
			libroEntity.setCantidadTotal(total + 1);
			libroEntity.setCantidadDisponible(disponibles + 1);
		} else {
			libroEntity = libroMapper.convertToEntity(libroDomain);
			libroEntity.setCantidadTotal(1);
			libroEntity.setCantidadDisponible(1);
		}
		libroDao.save(libroEntity);
		return RespuestaDomain.ok(null, "Registro del Libro Exitoso");
	}

	@Override
	public RespuestaDomain findByIsbn(String isbn) {
		LibroEntity e = libroDao.findByIsbn(isbn);
		if (Objects.isNull(e)) {
			return RespuestaDomain.error("No se encontro el Libro " + isbn);
		}
		return RespuestaDomain.ok(libroDao.findByIsbn(isbn), "Libro Encontrado");
	}

	@Override
	public RespuestaDomain findAll() {
		List<LibroEntity> list = libroDao.findAll();
		if (list.isEmpty()) {
			return RespuestaDomain.error("No se encontraron libros regustrados");
		}
		List<LibroDomain> listD = new ArrayList<>();
		for (LibroEntity e: list) {
			listD.add(libroMapper.convertToDomain(e));
		}
		return RespuestaDomain.ok(listD, "Exito");
	}

	public RespuestaDomain findAllPrestamo() {
		List<PrestamoEntity> list = prestaDao.findAll();
		if (list.isEmpty()) {
			return RespuestaDomain.error("No se encontraron libros regustrados");
		}
		List<PrestamoDomain> listD = new ArrayList<>();
		for (PrestamoEntity e: list) {
			listD.add(prestamoMapper.convertToDomain(e));
		}
		return RespuestaDomain.ok(listD, "Exito");
	}
	@Override
	public RespuestaDomain prestamoLibro(PrestamoSolicitudDomain prestamoSolicitudDomain) {
		PrestamoEntity prestamoEntity = new PrestamoEntity();
		boolean b = UtilPalindromic.esPalindromo(prestamoSolicitudDomain.getIsbn());
		if (b) {
			return RespuestaDomain.error("Los libros palíndromos solo se\n" + "pueden utilizar en la biblioteca");
		}
		b = UtilsFechaEntrega.prestamo(prestamoSolicitudDomain.getIsbn());
		if (b) { // isbn es mayor de 30 se entrega a los 15 dias max.
			Calendar c = Calendar.getInstance(); // fecha actual.
			prestamoEntity.setFechaPrestamo(UtilCalendar.getLocalDate(c));
			c.add(Calendar.DAY_OF_YEAR, 15);
			if (UtilCalendar.isDomingo(c)) {
				c.add(Calendar.DAY_OF_YEAR, 1);
				LocalDate entregaMax = UtilCalendar.getLocalDate(c);
				prestamoEntity.setFechaEntrega(entregaMax);
			} else {
				LocalDate entregaMax = UtilCalendar.getLocalDate(c);
				prestamoEntity.setFechaEntrega(entregaMax);
			}
			return regitrarPrestamo(prestamoSolicitudDomain, prestamoEntity);
		} // isbn es menor de 30 no tiene fecha de entrega.
		prestamoEntity.setFechaPrestamo(UtilCalendar.getLocalDate());
		prestamoEntity.setFechaEntrega(null);
		return regitrarPrestamo(prestamoSolicitudDomain, prestamoEntity);
	}

	private RespuestaDomain<Object> regitrarPrestamo(PrestamoSolicitudDomain prestamoSolicitudDomain,
			PrestamoEntity prestamoEntity) {
		RespuestaDomain responseUsuario = usuarioService.findByCorreo(prestamoSolicitudDomain.getCorreo());

		UsuarioEntity userEntity = null;
		if (!responseUsuario.isStatus()) {
			userEntity = crearUsuario(prestamoSolicitudDomain);
			if (Objects.isNull(userEntity)) {
				return RespuestaDomain.error("NO SE PUDO CREAR EL USUARIO");
			}
		} else {
			userEntity = (UsuarioEntity) responseUsuario.getData();
		}

		prestamoEntity.setUsuarioEntityCliente(userEntity);
		// Se busca el libro
		RespuestaDomain liroResponseEn = findByIsbn(prestamoSolicitudDomain.getIsbn());
		if (!liroResponseEn.isStatus()) {
			return RespuestaDomain.error("El libro no se encuentra registrado");
		}
		LibroEntity le = (LibroEntity) liroResponseEn.getData();
		if (le.getCantidadDisponible() <= 1) {
			return RespuestaDomain.error("El libro no tiene Stock para el prestamo");
		}
		le.setCantidadDisponible(le.getCantidadDisponible() - 1);
		libroDao.save(le);
		prestamoEntity.setObservaciones(OSERVACION_DEFAULT);
		prestamoEntity.setLibroEntity(le);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		RespuestaDomain userSesion = usuarioService.findByCorreo(authentication.getName());
		prestamoEntity.setUsuarioEntityBiblioteca((UsuarioEntity) userSesion.getData());
		prestaDao.save(prestamoEntity);

		return RespuestaDomain.ok(prestamoEntity, "Exito");
	}

	private UsuarioEntity crearUsuario(PrestamoSolicitudDomain prestamo) {
		UsuarioDomain user = new UsuarioDomain();
		PersonasDomain per = new PersonasDomain();
		per.setNombre(prestamo.getNombreUsuario());
		per.setApellido(prestamo.getApellidoUsuario());
		per.setIdentificacion(prestamo.getDocumento());
		per.setDireccion("");
		per.setTelefono("");
		per.setTipoIdentificacion("CC");

		user.setPersonasDomain(per);
		user.setCorreo(prestamo.getCorreo());
		user.setClave(per.getIdentificacion());
		user.setRol("C");
		return (UsuarioEntity) usuarioService.guarda(user).getData();
	}
}
