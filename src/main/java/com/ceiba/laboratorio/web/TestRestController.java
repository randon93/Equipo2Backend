package com.ceiba.laboratorio.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.laboratorio.models.dto.RespuestaDTO;

@RestController
public class TestRestController {
	
	@GetMapping
	public RespuestaDTO<String> test() {
		return RespuestaDTO.ok("respuesta test", "respuesta correcta");
	}

}
