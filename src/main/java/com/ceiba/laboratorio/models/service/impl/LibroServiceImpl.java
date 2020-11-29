package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.converter.LibroMapper;
import com.ceiba.laboratorio.models.dao.LibroDao;
import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.domain.PrestamoSolicitudDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.entity.LibroEntity;
import com.ceiba.laboratorio.models.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LibroServiceImpl implements LibroService {



    @Autowired
    private LibroMapper libroMapper;

    @Autowired
    private LibroDao libroDao;

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
    public RespuestaDomain prestamoLibro() {
        return null;
    }
}
