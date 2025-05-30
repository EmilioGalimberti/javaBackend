package com.example.demo.controller;

import com.example.demo.model.Posicion;
import com.example.demo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agencia/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/posicion/{idVehiculo}")
    public ResponseEntity<String> recibirPosicion(@PathVariable("idVehiculo") Integer idVehiculo) {
        Posicion posicion = vehiculoService.obtenerUltimaPosicion(idVehiculo);

        if (posicion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La posición del vehículo no se encontro");
        }

        if (!vehiculoService.verificarPosicion(posicion, idVehiculo)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay una prueba vigente para este vehiculo");
        };

        return ResponseEntity.ok("Se verifico la posición correctamente");
    }
}
