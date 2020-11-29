package com.ceiba.laboratorio.converter;

import com.ceiba.laboratorio.converter.mapper.LibroMapper;
import com.ceiba.laboratorio.converter.mapper.PrestamoMapper;
import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.domain.PrestamoDomain;
import com.ceiba.laboratorio.models.entity.LibroEntity;
import com.ceiba.laboratorio.models.entity.PrestamoEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class LibroMapperImpl implements LibroMapper {

    private PrestamoMapper mapper = Mappers.getMapper(PrestamoMapper.class);

    @Override
    public LibroEntity libroDomainToLibroEntityMapper(LibroDomain domain) {
        LibroEntity entity = new LibroEntity();
        entity.setCantidadDisponible(domain.getCantidadDisponible());
        entity.setCantidadTotal(domain.getCantidadTotal());
        entity.setId(domain.getId());
        entity.setIsbn(domain.getIsbn());
        entity.setNombre(domain.getNombre());
        Set<PrestamoEntity> prestamoEntities = new HashSet<>();
        for (PrestamoDomain d: domain.getPrestamoEtities()) {
            PrestamoEntity e = mapper.prestamoDomainToPrestamoEntity(d);
            prestamoEntities.add(e);
        }
        entity.setPrestamoEtities(prestamoEntities);
        return entity;
    }

    @Override
    public LibroDomain libroEntityToLibroDomainMapper(LibroEntity enity) {
        LibroDomain domain = new LibroDomain();
        domain.setCantidadDisponible(enity.getCantidadDisponible());
        domain.setCantidadTotal(enity.getCantidadTotal());
        domain.setId(enity.getId());
        domain.setIsbn(enity.getIsbn());
        domain.setNombre(enity.getNombre());
        Set<PrestamoDomain> prestamoDomains = new HashSet<>();
        for (PrestamoEntity e : enity.getPrestamoEtities()) {
            PrestamoDomain d = mapper.prestamosEntityToPrestamoDomain(e);
            prestamoDomains.add(d);
        }
        domain.setPrestamoEtities(prestamoDomains);
        return null;
    }
}
