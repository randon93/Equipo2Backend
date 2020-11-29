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
    private String correo;
    private String documento;


}
