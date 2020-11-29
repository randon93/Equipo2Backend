package com.ceiba.laboratorio.converter.mapper;

import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.entity.LibroEntity;


public interface LibroMapper {


    LibroEntity libroDomainToLibroEntityMapper(LibroDomain domain);

    LibroDomain libroEntityToLibroDomainMapper(LibroEntity accionEntity);
}
