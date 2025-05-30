package com.example.demo.controller;

import com.example.demo.model.Empleado;
import com.example.demo.model.Interesado;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.InteresadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agencia/interesados")
public class InteresadoController {

    private InteresadoService service;

    public InteresadoController(InteresadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Interesado>> findAll() {
        List<Interesado> interesados = service.findAll();
        return ResponseEntity.ok(interesados);
    }
}
