package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Pruebas")
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FECHA_HORA_INICIO")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private LocalDateTime fechaHoraFin;

    @Column(name = "COMENTARIOS", length = 500)
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "Pruebas_Vehiculos_FK"))
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "ID_INTERESADO", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "Pruebas_Interesados_FK"))
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "LEGAJO", foreignKey = @ForeignKey(name = "Pruebas_Empleados_FK"))
    private Empleado empleado;
}
