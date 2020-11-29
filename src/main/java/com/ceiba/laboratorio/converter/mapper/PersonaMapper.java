package com.ceiba.laboratorio.converter.mapper;

import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.entity.PersonasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {

    PersonasDomain personaEntityToPersonaDomainMapper(PersonasEntity entity);

    PersonasEntity personaDomainToPersonaEntityMapper(PersonasDomain domain);
}
