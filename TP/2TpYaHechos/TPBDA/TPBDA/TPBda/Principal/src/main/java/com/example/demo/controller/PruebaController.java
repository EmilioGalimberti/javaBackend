package com.example.demo.controller;

import com.example.demo.dtos.PruebaDTO;
import com.example.demo.model.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.PruebaService;

import java.util.List;

@RestController
@RequestMapping("/agencia/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    @GetMapping
    public ResponseEntity<List<Prueba>> findAll() {
        List<Prueba> pruebas = pruebaService.findAll();
        return ResponseEntity.ok(pruebas);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearPrueba(@RequestBody PruebaDTO pruebaDTO) {
        pruebaService.crearPrueba(pruebaDTO);
        return ResponseEntity.ok("Prueba creada exitosamente");
    }

    @GetMapping("/curso")
    public List<Prueba> listarPruebasEnCurso() {
        return pruebaService.obtenerPruebasEnCurso();
    }

    @PostMapping("/finalizar/{idPrueba}")
    public Prueba finalizarPrueba(@PathVariable("idPrueba") Integer idPrueba, @RequestBody String comentario) {
        return pruebaService.finalizarPrueba(idPrueba, comentario);
    }

}

