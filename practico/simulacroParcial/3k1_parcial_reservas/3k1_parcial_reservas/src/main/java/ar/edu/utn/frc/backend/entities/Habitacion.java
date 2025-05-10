package ar.edu.utn.frc.backend.entities;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Habitacion")

public class Habitacion {
    @Id
    private int id;
    private String tipo;

    public Habitacion(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    // Constructor vacío necesario para JPA
    public Habitacion() {

    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }


    @Override
    public String toString() {
        return "Habitacion{id=" + id + ", tipo='" + tipo + "'}";
    }
}