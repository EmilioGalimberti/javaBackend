package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;

import java.util.Objects;

//Notacion JPA
//La estructura de la tabla Cliente se define en la clase a través de las anotaciones JPA (Java Persistence API).
// Veamos las anotaciones clave: `Cliente.java`
//1. `@Entity`: Indica que esta clase es una entidad que debe ser mapeada a una tabla en la base de datos.
//2. `@Table(name = "Cliente")`: Especifica el nombre de la tabla en la base de datos.
//3. `@Id`: Marca el campo como la clave primaria de la tabla.
@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    private int id;
    private String nombre;

    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Constructor vacío necesario para JPA
    public Cliente() {

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "'}";
    }
}