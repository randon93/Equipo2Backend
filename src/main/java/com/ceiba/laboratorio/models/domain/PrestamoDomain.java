package com.ceiba.laboratorio.models.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PrestamoDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaEntrega;
    private LocalDate fechaEntregado;
    private String observaciones;
    private UsuarioDomain usuarioDomainCliente;
    private UsuarioDomain usuarioDomainBiblioteca;
    private LibroDomain libroDomain;
}
