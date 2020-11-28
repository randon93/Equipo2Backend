package com.ceiba.laboratorio.controller;

import com.ceiba.laboratorio.models.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;


}
