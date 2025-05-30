package com.example.demo.repository;

import com.example.demo.model.Posicion;
import org.springframework.data.repository.CrudRepository;

public interface PosicionRepository extends CrudRepository<Posicion, Integer> {

    // Encuentra la última posición de un vehículo por ID, ordenado por fecha y hora descendente
    Posicion findFirstByVehiculoIdOrderByFechaHoraDesc(Integer id);
}

