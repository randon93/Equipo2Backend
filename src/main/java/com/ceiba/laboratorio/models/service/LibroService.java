package com.ceiba.laboratorio.models.service;

import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;

public interface LibroService {

    RespuestaDomain guardarLibro(LibroDomain libroEntity);

    RespuestaDomain findByIsbn(String isbn);

    RespuestaDomain prestamoLibro();
}
