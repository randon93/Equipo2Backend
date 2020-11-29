package com.ceiba.laboratorio.converter;

import com.ceiba.laboratorio.converter.mapper.PersonaMapper;
import com.ceiba.laboratorio.converter.mapper.PrestamoMapper;
import com.ceiba.laboratorio.converter.mapper.UsuarioMapper;
import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.PrestamoDomain;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.entity.PersonasEntity;
import com.ceiba.laboratorio.models.entity.PrestamoEntity;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    private PrestamoMapper mapperPrestamos = Mappers.getMapper(PrestamoMapper.class);
    private PersonaMapper mapperPersona = Mappers.getMapper(PersonaMapper.class);

    @Override
    public UsuarioEntity usuarioDomainToUsuarioEnity(UsuarioDomain domain) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setClave(domain.getClave());
        entity.setCorreo(domain.getCorreo());
        entity.setId(domain.getId());
        entity.setRol(domain.getRol());

        Set<PrestamoEntity> prestamoMapper = new HashSet<>();
        for (PrestamoDomain d : domain.getUsuarioClientes()) {
            PrestamoEntity e = mapperPrestamos.prestamoDomainToPrestamoEntity(d);
            prestamoMapper.add(e);
        }
        entity.setUsuarioClientes(prestamoMapper);
        prestamoMapper.clear();

        for (PrestamoDomain d : domain.getUsuarioBibliotecas()) {
            PrestamoEntity e = mapperPrestamos.prestamoDomainToPrestamoEntity(d);
            prestamoMapper.add(e);
        }
        entity.setUsuarioBibliotecas(prestamoMapper);

        PersonasEntity pd = mapperPersona.personaDomainToPersonaEntityMapper(domain.getPersonasDomain());
        entity.setPersonasEntity(pd);
        return entity;
    }

    @Override
    public UsuarioDomain usuarioEntityToUsuarioDomain(UsuarioEntity entity) {
        UsuarioDomain domain = new UsuarioDomain();
        domain.setClave(entity.getClave());
        domain.setCorreo(entity.getCorreo());
        domain.setId(entity.getId());
        domain.setRol(entity.getRol());

        Set<PrestamoDomain> prestamoMapper = new HashSet<>();
        for (PrestamoEntity e : entity.getUsuarioClientes()) {
            PrestamoDomain d = mapperPrestamos.prestamosEntityToPrestamoDomain(e);
            prestamoMapper.add(d);
        }
        domain.setUsuarioClientes(prestamoMapper);
        prestamoMapper.clear();

        for (PrestamoEntity e : entity.getUsuarioBibliotecas()) {
            PrestamoDomain d = mapperPrestamos.prestamosEntityToPrestamoDomain(e);
            prestamoMapper.add(d);
        }
        domain.setUsuarioBibliotecas(prestamoMapper);

        PersonasDomain pd = mapperPersona.personaEntityToPersonaDomainMapper(entity.getPersonasEntity());
        domain.setPersonasDomain(pd);
        return domain;
    }
}
