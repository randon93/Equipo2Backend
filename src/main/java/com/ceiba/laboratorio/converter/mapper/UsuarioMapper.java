package com.ceiba.laboratorio.converter.mapper;

import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;


public interface UsuarioMapper {

    UsuarioEntity usuarioDomainToUsuarioEnity(UsuarioDomain domain);

    UsuarioDomain usuarioEntityToUsuarioDomain(UsuarioEntity entity);
}
