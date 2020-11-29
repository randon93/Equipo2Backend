package com.ceiba.laboratorio.models.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class LibroServiceImpl implements LibroService {

    private static final String OSERVACION_DEFAULT = "LIBRO PRESTADO EN PREFECTAS CONDICIONES";

    @Autowired
    private LibroMapper libroMapper;

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
            libroDao.save(libroEntity);
        }
        return RespuestaDomain.ok(null, "Registro del Libro Exitoso");
    }

    @Override
    public RespuestaDomain findByIsbn(String isbn) {
        LibroEntity e = libroDao.findByIsbn(isbn);
        if (Objects.isNull(e)) {
            return RespuestaDomain.error("No se encontro el Libro " + isbn);
        }
        return RespuestaDomain.ok(libroDao.findByIsbn(isbn),"Libro Encontrado" );
    }

    @Override
    public RespuestaDomain prestamoLibro(PrestamoSolicitudDomain prestamoSolicitudDomain) {
        PrestamoEntity prestamoEntity = new PrestamoEntity();
        boolean b = UtilPalindromic.esPalindromo(prestamoSolicitudDomain.getIsbn());
        if (b){
            return RespuestaDomain.error("Los libros palíndromos solo se\n" +
                    "pueden utilizar en la biblioteca");
        }
        b = UtilsFechaEntrega.prestamo(prestamoSolicitudDomain.getIsbn());
        if (b) { //es mayor de 30 se entrega a los 15 dias max.
            RespuestaDomain responseMayorTreinta = isbnMayorTreinta(prestamoSolicitudDomain);
            if (!responseMayorTreinta.isStatus()) {
                return RespuestaDomain.error(responseMayorTreinta.getMensaje());
            }

        }
        return null;
    }

    private RespuestaDomain<Object> isbnMayorTreinta(PrestamoSolicitudDomain prestamoSolicitudDomain) {
        PrestamoEntity prestamoEntity = new PrestamoEntity();
        prestamoEntity.setFechaPrestamo(UtilCalendar.getLocalDate());
        LocalDate quienceDiasMax = UtilCalendar.getLocalDate(Calendar.getInstance());
        prestamoEntity.setFechaEntrega(quienceDiasMax);
        RespuestaDomain responseUsuario = usuarioService.findByCorreo(prestamoSolicitudDomain.getCorreo());
        if (!responseUsuario.isStatus()) {
            UsuarioEntity userEntity = crearUsuario(prestamoSolicitudDomain);
            if (Objects.isNull(userEntity)){
                return RespuestaDomain.error("NO SE PUDO CREAR EL USUARIO");
            }
            prestamoEntity.setUsuarioEntityCliente(userEntity);
            //Se busca el libro
            RespuestaDomain liroResponseEn =  findByIsbn(prestamoSolicitudDomain.getIsbn());
            if (!liroResponseEn.isStatus() ) {
                return RespuestaDomain.error("El libro no se encuentra registrado");
            }
            LibroEntity le = (LibroEntity) liroResponseEn.getData();
            if (le.getCantidadDisponible() <= 1) {
                return RespuestaDomain.error("El libro no tiene Stock para el prestamo");
            }
            le.setCantidadDisponible(le.getCantidadDisponible() - 1 );
            libroDao.save(le);
            prestamoEntity.setObservaciones(OSERVACION_DEFAULT);
            prestamoEntity.setLibroEntity(le);
            prestaDao.save(prestamoEntity);
        }
        return RespuestaDomain.ok(prestamoEntity, "Exito");
    }

//    private RespuestaDomain

    private UsuarioEntity crearUsuario(PrestamoSolicitudDomain prestamo) {
        UsuarioDomain user = new UsuarioDomain();
        PersonasDomain per = new PersonasDomain();
        per.setNombre(prestamo.getNombreUsuario());
        per.setApellido(prestamo.getApellidoUsuario());
        per.setIdentificacion(prestamo.getDocumento());
        user.setPersonasDomain(per);
        user.setCorreo(prestamo.getCorreo());
        user.setClave(per.getIdentificacion());
        return (UsuarioEntity) usuarioService.guarda(user).getData();
    }
}
