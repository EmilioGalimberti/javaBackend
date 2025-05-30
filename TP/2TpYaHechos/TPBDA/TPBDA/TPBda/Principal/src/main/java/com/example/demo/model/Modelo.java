package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Modelos")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "Modelo_Marca_Fk"))
    private Marca marca;
}
