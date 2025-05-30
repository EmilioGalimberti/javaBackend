package com.example.demo.repository;

import com.example.demo.model.Notificacion;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface NotificacionRepository extends CrudRepository<Notificacion, Integer> {
    Notificacion findById(int id);
//    // Método para buscar todas las notificaciones cuyo motivo comienza con un prefijo específico
//    List<Notificacion> findByMotivoStartingWith(String prefix);
//
//    List<Notificacion> findByIdPrueba(Integer idPrueba);
//
//    List<Notificacion> findByMotivoContainingIgnoreCase(String incidente);
}