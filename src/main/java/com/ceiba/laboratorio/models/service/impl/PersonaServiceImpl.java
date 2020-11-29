package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.converter.PersonaMapper;
import com.ceiba.laboratorio.models.dao.PersonaDao;
import com.ceiba.laboratorio.models.domain.PersonasDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.entity.PersonasEntity;
import com.ceiba.laboratorio.models.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public RespuestaDomain guardarPersona(PersonasDomain personaDomain) {
        PersonasEntity e = personaMapper.convertToEntity(personaDomain);
        personaDao.save(e);
        return null;
    }

    @Override
    public RespuestaDomain findByIdentidad(String id) {
        PersonasEntity e = personaDao.findByIdentificacion(id);
        if (Objects.isNull(e)) {
            return RespuestaDomain.error("No se encontro al cliente " + id);
        }
        return RespuestaDomain.ok(e, "Exito");
    }

    @Override
    public RespuestaDomain findAll() {
        List<PersonasEntity> list = personaDao.findAll();
        if (list.isEmpty() || Objects.isNull(list)) {
            return RespuestaDomain.error("NO TIENE CLIENTES REGISTRADOS");
        }
        return RespuestaDomain.ok(list, "Exito");
    }
}
