package com.ceiba.laboratorio.converter;

import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.entity.PersonasEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PersonasDomain convertToDomain(PersonasEntity post) {
        PersonasDomain postDto = modelMapper.map(post, PersonasDomain.class);
        return postDto;
    }

    public PersonasEntity convertToEntity(PersonasDomain post) {
        PersonasEntity postDto = modelMapper.map(post, PersonasEntity.class);
        return postDto;
    }

}
