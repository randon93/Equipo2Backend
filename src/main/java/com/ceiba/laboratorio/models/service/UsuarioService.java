package com.ceiba.laboratorio.models.service;


import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;

public interface UsuarioService {

    RespuestaDomain findByCorreo(String correo);

    RespuestaDomain findByIdentidad(String identidad);

    RespuestaDomain findAll();

    RespuestaDomain guarda(UsuarioDomain usuarioDomain);

}
