package com.ceiba.laboratorio.web;

import com.ceiba.laboratorio.util.UtilCalendar;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.laboratorio.models.dto.RespuestaDTO;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

@RestController
@Log4j2
public class TestRestController {
	
	@GetMapping
	public RespuestaDTO<String> test() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 15);
		return RespuestaDTO.ok(UtilCalendar.formatearCalendar(c), "respuesta correcta");
	}

}
