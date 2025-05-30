package com.example.demo.service;

import com.example.demo.model.Empleado;
import com.example.demo.model.Interesado;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.InteresadoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class InteresadoService {

    private final InteresadoRepository interesadoRepository;

    public InteresadoService(InteresadoRepository interesadoRepository) {
        this.interesadoRepository = interesadoRepository;
    }

    public Interesado findById(Integer id) {
        return interesadoRepository.findById(id).get();
    }

    public List<Interesado> findAll() {
        return (List<Interesado>) interesadoRepository.findAll();
    }
}
