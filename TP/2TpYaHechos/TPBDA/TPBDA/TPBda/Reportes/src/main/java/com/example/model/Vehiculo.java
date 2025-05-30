package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PATENTE")
    private String patente;

    @Column(name = "ANIO")
    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "ID_MODELO", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "Vehiculos_Modelos_FK"))
    private Modelo modelo;
}
