package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.converter.LibroMapper;
import com.ceiba.laboratorio.converter.UsuarioMapper;
import com.ceiba.laboratorio.models.dao.PersonaDao;
import com.ceiba.laboratorio.models.dao.UsuarioDao;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.entity.PersonasEntity;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import com.ceiba.laboratorio.models.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public RespuestaDomain findByCorreo(String correo) {
        UsuarioEntity e = usuarioDao.findByCorreo(correo);
        if (Objects.isNull(e)) {
            return RespuestaDomain.error("No se encotnro el usuario " + correo);
        }
        return RespuestaDomain.ok(e, "Exito");
    }

    @Override
    public RespuestaDomain findAll() {
        List<UsuarioEntity> list = usuarioDao.findAll();
        if(list.isEmpty()){
            return RespuestaDomain.error("Ningun Usuario Resgistrado.");
        }
        return RespuestaDomain.ok(list, "Exito");
    }

    @Override
    public RespuestaDomain guarda(UsuarioDomain usuarioDomain) {
        UsuarioEntity ue = usuarioMapper.convertToEntity(usuarioDomain);
        String claveEncryp = passwordEncoder.encode(ue.getClave());
        ue.setClave(claveEncryp);
        personaDao.save(ue.getPersonasEntity());
        usuarioDao.save(ue);
        return RespuestaDomain.ok(ue, "Exito");
    }
}
