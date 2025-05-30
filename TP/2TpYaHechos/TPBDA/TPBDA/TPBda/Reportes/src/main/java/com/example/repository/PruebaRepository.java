package com.example.repository;

import com.example.model.Prueba;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PruebaRepository extends CrudRepository<Prueba, Integer> {


    @Query("SELECT p FROM Prueba p WHERE p.fechaHoraFin IS NULL")
    List<Prueba> findPruebasEnCurso();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Prueba p WHERE p.vehiculo.id = :idVehiculo " +
            "AND p.fechaHoraFin is null")
    boolean existePruebaActiva(@Param("idVehiculo") Integer idVehiculo);

    Prueba findPruebaById(Integer id);

    List<Prueba> findByVehiculo_Id(Integer vehiculoId);

    @Query("SELECT p FROM Prueba p JOIN Notificacion n ON p.id = n.prueba.id WHERE UPPER(n.motivo) LIKE 'INCIDENTE%'")
    List<Prueba> findPruebasConIncidentes();

   /* @Query("SELECT p FROM Prueba p JOIN Notificacion n ON p.id = n.idPrueba " +
            "WHERE UPPER(n.motivo) LIKE 'INCIDENTE%' AND p.empleado.legajo = :idEmpleado")
    List<Prueba> findPruebasConIncidentesByEmpleado(@Param("idEmpleado") Integer idEmpleado);*/

    @Query("SELECT p FROM Prueba p JOIN Notificacion n ON p.id = n.prueba.id WHERE UPPER(n.motivo) LIKE '%INCIDENTE%' AND p.empleado.legajo = :legajoEmpleado")
    List<Prueba> findPruebasConIncidentesByEmpleado(@Param("legajoEmpleado") Integer legajoEmpleado);
}
