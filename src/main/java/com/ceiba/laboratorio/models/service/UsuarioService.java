package com.ceiba.laboratorio.models.service;

import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;

public interface UsuarioService {
	RespuestaDomain findById(Long id);
	RespuestaDomain findByCorreo(String correo);
	RespuestaDomain save(UsuarioEntity usuarioNuevo);
    RespuestaDomain findAll();
    RespuestaDomain update(UsuarioEntity usuarioNuevo);
    RespuestaDomain delete(Long id);
    
}
