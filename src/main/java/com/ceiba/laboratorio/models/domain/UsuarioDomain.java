package com.ceiba.laboratorio.models.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class UsuarioDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String correo;
    private String clave;
    private String rol;
    private Set<PrestamoDomain> usuarioClientes = new HashSet<>();
    private Set<PrestamoDomain> usuarioBibliotecas = new HashSet<>();
    private PersonasDomain personasDomain;
}
