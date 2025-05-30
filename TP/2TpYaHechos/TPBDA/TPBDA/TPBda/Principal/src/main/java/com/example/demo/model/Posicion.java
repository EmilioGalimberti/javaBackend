package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Posiciones")
public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;

    @Column(name = "FECHA_HORA")
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "Posiciones_Vehiculos_FK"))
    private Vehiculo vehiculo;
}
