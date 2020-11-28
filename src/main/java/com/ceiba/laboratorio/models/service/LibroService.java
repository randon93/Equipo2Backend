package com.ceiba.laboratorio.models.service;

import com.ceiba.laboratorio.models.dto.RespuestaDTO;

public interface LibroService {

    RespuestaDTO guardarLibro(LibroEntity libroEntity);

    RespuestaDTO findByIsbn(String isbn);

    RespuestaDTO prestamoLibro();
}
