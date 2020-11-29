package com.ceiba.laboratorio.converter;

import com.ceiba.laboratorio.converter.mapper.UsuarioMapper;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;

public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioEntity usuarioDomainToUsuarioEnity(UsuarioDomain domain) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setClave(domain.getClave());
        entity.setCorreo(domain.getCorreo());
        entity.setId(domain.getId());
        entity.setRol(domain.getRol());

//        entity.setUsuarioClientes();
//        entity.setUsuarioBibliotecas();
//
//        entity.setPersonasEntity();
        return null;
    }

    @Override
    public UsuarioDomain usuarioEntityToUsuarioDomain(UsuarioEntity entity) {
        return null;
    }
}
