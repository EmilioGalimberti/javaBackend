package com.example.demo.repository;

import com.example.demo.model.Prueba;
import com.example.demo.model.Vehiculo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {

}