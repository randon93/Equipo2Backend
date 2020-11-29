package com.ceiba.laboratorio.controller;

import com.ceiba.laboratorio.models.domain.LibroDomain;
import com.ceiba.laboratorio.models.domain.PrestamoSolicitudDomain;
import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping("/guardar-libro")
    @ResponseBody
    private RespuestaDomain guardarLibro(@RequestBody LibroDomain libroDomain) {
        return libroService.guardarLibro(libroDomain);
    }

}
