package com.example.repository;


import com.example.model.Interesado;
import org.springframework.data.repository.CrudRepository;

public interface InteresadoRepository extends CrudRepository<Interesado, Integer> {
    Interesado findById(int id);
}