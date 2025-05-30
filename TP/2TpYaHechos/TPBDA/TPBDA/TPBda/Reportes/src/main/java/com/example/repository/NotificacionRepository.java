package com.example.repository;


import com.example.model.Notificacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificacionRepository extends CrudRepository<Notificacion, Integer> {
    Notificacion findById(int id);
//    // Método para buscar todas las notificaciones cuyo motivo comienza con un prefijo específico
    List<Notificacion> findByMotivoStartingWith(String prefix);
//
    List<Notificacion> findByPruebaId(Integer idPrueba);
//
    List<Notificacion> findByMotivoContainingIgnoreCase(String incidente);
}