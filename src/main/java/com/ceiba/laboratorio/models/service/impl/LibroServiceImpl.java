package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.models.dao.LibroDao;
import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.entity.LibroEntity;
import com.ceiba.laboratorio.models.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroDao libroDao;

    @Override
    public RespuestaDomain guardarLibro(LibroDomain libroDomain) {
        LibroEntity libroEntity = libroDao.findByIsbn(libroDomain.getIsbn());
        if (Objects.nonNull(libroEntity)){
            Integer total = libroEntity.getCantidadTotal();
            Integer disponibles = libroEntity.getCantidadDisponible();
            libroEntity.setCantidadTotal(total + 1);
            libroEntity.setCantidadDisponible(disponibles + 1);
        }else {
            libroEntity = new LibroEntity();
        }
        return null;
    }

    @Override
    public RespuestaDomain findByIsbn(String isbn) {
        return null;
    }

    @Override
    public RespuestaDomain prestamoLibro() {
        return null;
    }
}
