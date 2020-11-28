package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.dto.RespuestaDTO;
import com.ceiba.laboratorio.models.service.PersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Override
    public RespuestaDTO guardarPersona(PersonasDomain personaDomain) {
        return null;
    }

    @Override
    public RespuestaDTO findByCorreo(String correo) {
        return null;
    }

    @Override
    public RespuestaDTO findAll() {
        return null;
    }
}
