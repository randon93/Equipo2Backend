package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.service.PersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Override
    public RespuestaDomain guardarPersona(PersonasDomain personaDomain) {
        return null;
    }

    @Override
    public RespuestaDomain findByCorreo(String correo) {
        return null;
    }

    @Override
    public RespuestaDomain findAll() {
        return null;
    }
}
