package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LEGAJO")
    private Integer legajo;

    @Column(name = "NOMBRE", length = 30)
    private String nombre;

    @Column(name = "APELLIDO", length = 50)
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO")
    private Integer telefono;
}
