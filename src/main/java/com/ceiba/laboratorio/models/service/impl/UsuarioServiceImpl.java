package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public RespuestaDomain findByCorreo(String correo) {
        return null;
    }

    @Override
    public RespuestaDomain findAll() {
        return null;
    }

    @Override
    public RespuestaDomain guarda(UsuarioDomain personasDomain) {
        return null;
    }
}
