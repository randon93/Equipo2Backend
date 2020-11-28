package com.ceiba.laboratorio.models.service;

import com.ceiba.laboratorio.models.dto.RespuestaDTO;

public interface PersonaService {

    RespuestaDTO guardarPersona(PersonaDomain personaDomain);

    RespuestaDTO findByCorreo(String correo);

    RespuestaDTO findAll();

}
