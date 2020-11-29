package com.ceiba.laboratorio.converter;

import com.ceiba.laboratorio.models.domain.PrestamoDomain;
import com.ceiba.laboratorio.models.entity.PrestamoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrestamoMapper {


    @Autowired
    private ModelMapper modelMapper;

    public PrestamoDomain convertToDomain(PrestamoEntity post) {
        PrestamoDomain postDto = modelMapper.map(post, PrestamoDomain.class);
        return postDto;
    }

    public PrestamoEntity convertToEntity(PrestamoDomain post) {
        PrestamoEntity postDto = modelMapper.map(post, PrestamoEntity.class);
        return postDto;
    }

}
