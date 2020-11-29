package com.ceiba.laboratorio.models.service.impl;

import com.ceiba.laboratorio.models.dao.UsuarioDao;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	UsuarioDao usuarioDao;

	@Override
	public RespuestaDomain findByCorreo(String correo) {
		// TODO Auto-generated method stub
		return usuarioDao.findByCorreo(correo);
	}

	@Override
	public RespuestaDomain save(UsuarioEntity usuarioNuevo) {
		// TODO Auto-generated method stub
		if(usuarioNuevo!= null) {
			return usuarioDao.save(usuarioNuevo);
		}
		return null;
	}

	@Override
	public RespuestaDomain findAll() {
		// TODO Auto-generated method stub
		return usuarioDao.findAll();
	}

	@Override
	public RespuestaDomain update(UsuarioEntity usuarioNuevo) {
		// TODO Auto-generated method stub
		return usuarioDao.save(usuarioNuevo);
	}

	@Override
	public RespuestaDomain delete(Long id) {
		// TODO Auto-generated method stub
			if(usuarioDao.findById(id)!=null) {
				return usuarioDao.deleteById(id);
			}
		return null;
	}

	@Override
	public RespuestaDomain findById(Long id){
		return usuarioDao.findById(id);
	}
	
	
}
