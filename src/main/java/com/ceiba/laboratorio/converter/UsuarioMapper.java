package com.ceiba.laboratorio.converter;

import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {


    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDomain convertToDomain(UsuarioEntity post) {
        UsuarioDomain postDto = modelMapper.map(post, UsuarioDomain.class);
        return postDto;
    }

    public UsuarioEntity convertToEntity(UsuarioDomain post) {
        UsuarioEntity postDto = modelMapper.map(post, UsuarioEntity.class);
        return postDto;
    }
}
