package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.models.dao.PersonaDao;
import com.ceiba.laboratorio.models.dao.UsuarioDao;
import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PersonaDao personaDao;


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
