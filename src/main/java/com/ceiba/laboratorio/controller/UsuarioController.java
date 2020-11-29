package com.ceiba.laboratorio.controller;

import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.domain.UsuarioDomain;
import com.ceiba.laboratorio.models.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.laboratorio.models.service.PersonaService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/guardar")
	@ResponseBody
	public RespuestaDomain guardar(@RequestBody UsuarioDomain usuarioDomain) {
		return usuarioService.guarda(usuarioDomain);
	}

	@GetMapping("/buscar-correo/{correo}")
	@ResponseBody
	public RespuestaDomain buscarCorreo(@PathVariable String correo) {
		return usuarioService.findByCorreo(correo);
	}

	@GetMapping
	@ResponseBody
	public RespuestaDomain findAll() {
		return usuarioService.findAll();
	}

}
