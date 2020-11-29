package com.ceiba.laboratorio.converter.mapper;

import com.ceiba.laboratorio.models.domain.PrestamoDomain;
import com.ceiba.laboratorio.models.entity.PrestamoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PrestamoMapper {

    PrestamoDomain prestamosEntityToPrestamoDomain(PrestamoEntity entity);

    PrestamoEntity prestamoDomainToPrestamoEntity(PrestamoDomain prestamoDomain);
}
