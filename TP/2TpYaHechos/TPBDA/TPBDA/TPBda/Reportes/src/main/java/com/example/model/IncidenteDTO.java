package com.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class IncidenteDTO {
    private Long idIncidente;
    private String motivo;
    private Long idPrueba;
    private Long idVehiculo;
    private Long idInteresado;
    private Long idEmpleado;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String comentarios;
}
