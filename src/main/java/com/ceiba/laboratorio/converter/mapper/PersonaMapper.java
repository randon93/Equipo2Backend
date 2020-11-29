package com.ceiba.laboratorio.converter.mapper;

import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.entity.PersonasEntity;

public interface PersonaMapper {

    PersonasDomain personaEntityToPersonaDomainMapper(PersonasEntity entity);

    PersonasEntity personaDomainToPersonaEntityMapper(PersonasDomain domain);
}
