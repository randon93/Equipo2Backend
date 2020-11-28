package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.models.dto.RespuestaDTO;
import com.ceiba.laboratorio.models.service.LibroService;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements LibroService {

    @Override
    public RespuestaDTO guardarLibro(LibroEntity libroEntity) {
        return null;
    }

    @Override
    public RespuestaDTO findByIsbn(String isbn) {
        return null;
    }

    @Override
    public RespuestaDTO prestamoLibro() {
        return null;
    }
}
