package com.ceiba.laboratorio.models.service;

import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;

public interface PersonaService {

    RespuestaDomain guardarPersona(PersonasDomain personaDomain);

    RespuestaDomain findByCorreo(String correo);

    RespuestaDomain findAll();

}
