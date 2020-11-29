package com.ceiba.laboratorio.models.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class PersonasDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private Set<UsuarioDomain> usuarioEntities = new HashSet<>();
}
