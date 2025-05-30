package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Interesados")
public class Interesado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TIPO_DOCUMENTO", length = 3)
    private String tipoDocumento;

    @Column(name = "DOCUMENTO", length = 10)
    private String documento;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "APELLIDO", length = 50)
    private String apellido;

    @Column(name = "RESTRINGIDO")
    private Boolean restringido = false;

    @Column(name = "NRO_LICENCIA")
    private Integer nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private LocalDateTime fechaVencimientoLicencia;
}
