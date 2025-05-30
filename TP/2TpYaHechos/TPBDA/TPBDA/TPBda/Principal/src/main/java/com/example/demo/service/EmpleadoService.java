package com.example.demo.service;

import com.example.demo.model.Interesado;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado findByLegajo(Integer legajo) {
        return empleadoRepository.findById(legajo).get();
    }

}
