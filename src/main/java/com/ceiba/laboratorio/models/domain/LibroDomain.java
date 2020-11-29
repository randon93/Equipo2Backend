package com.ceiba.laboratorio.models.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class LibroDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String isbn;
    private String nombre;
    private Integer cantidadTotal;
    private Integer cantidadDisponible;
    private Set<PrestamoDomain> prestamoEtities = new HashSet<>();
}
