package com.ceiba.laboratorio.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoSolicitudDomain {

    private String isbn;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String clave;
    private String correo;
    private String direccion;
    private String telefono;
    private String tipoIdentificacion;
    private String identificacion;

}
