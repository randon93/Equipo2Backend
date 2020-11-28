package com.ceiba.laboratorio.models.service;

import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.dto.RespuestaDTO;

public interface LibroService {

    RespuestaDTO guardarLibro(LibroDomain libroEntity);

    RespuestaDTO findByIsbn(String isbn);

    RespuestaDTO prestamoLibro();
}
