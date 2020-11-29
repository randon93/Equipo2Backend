package com.ceiba.laboratorio.converter;

import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.entity.LibroEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {

    @Autowired
    private ModelMapper modelMapper;

    public LibroDomain convertToDomain(LibroEntity post) {
        LibroDomain postDto = modelMapper.map(post, LibroDomain.class);
        return postDto;
    }
    public LibroEntity convertToEntity(LibroDomain post) {
        LibroEntity postDto = modelMapper.map(post, LibroEntity.class);
        return postDto;
    }

}
