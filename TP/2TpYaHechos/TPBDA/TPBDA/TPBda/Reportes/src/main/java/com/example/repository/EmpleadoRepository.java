package com.example.repository;

import com.example.model.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

    Empleado findByNombre(String nombre);
}