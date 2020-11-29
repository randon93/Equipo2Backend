package com.ceiba.laboratorio.controller;

import com.ceiba.laboratorio.models.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

}
