package com.ceiba.laboratorio.converter;

import com.ceiba.laboratorio.converter.mapper.PersonaMapper;
import com.ceiba.laboratorio.converter.mapper.PrestamoMapper;
import com.ceiba.laboratorio.converter.mapper.UsuarioMapper;
import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.entity.PersonasEntity;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PersonaMapperImpl implements PersonaMapper {

    private UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);

    @Override
    public PersonasDomain personaEntityToPersonaDomainMapper(PersonasEntity entity) {
        PersonasDomain domain = new PersonasDomain();
        domain.setApellido(entity.getApellido());
        domain.setDireccion(entity.getDireccion());
        domain.setId(entity.getId());
        domain.setIdentificacion(entity.getIdentificacion());
        domain.setNombre(entity.getNombre());
        domain.setTelefono(entity.getTelefono());
        domain.setTipoIdentificacion(entity.getTipoIdentificacion());
        Set<UsuarioDomain> usuarioDomains = new HashSet<>();
        for (UsuarioEntity e : entity.getUsuarioEntities()) {
            UsuarioDomain d = mapper.usuarioEntityToUsuarioDomain(e);
            usuarioDomains.add(d);
        }
        domain.setUsuarioEntities(usuarioDomains);
        return domain;
    }

    @Override
    public PersonasEntity personaDomainToPersonaEntityMapper(PersonasDomain domain) {

        PersonasEntity entity = new PersonasEntity();
        entity.setApellido(domain.getApellido());
        entity.setDireccion(domain.getDireccion());
        entity.setId(domain.getId());
        entity.setIdentificacion(domain.getIdentificacion());
        entity.setNombre(domain.getNombre());
        entity.setTelefono(domain.getTelefono());
        entity.setTipoIdentificacion(domain.getTipoIdentificacion());
        Set<UsuarioEntity> usuarioEntities = new HashSet<>();
        for (UsuarioDomain d : domain.getUsuarioEntities()) {
            UsuarioEntity e = mapper.usuarioDomainToUsuarioEnity(d);
            usuarioEntities.add(e);
        }
        entity.setUsuarioEntities(usuarioEntities);
        return entity;
    }
}
