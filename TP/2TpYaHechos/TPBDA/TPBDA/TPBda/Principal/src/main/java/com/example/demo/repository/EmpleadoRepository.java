package com.example.demo.repository;

import com.example.demo.model.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

}