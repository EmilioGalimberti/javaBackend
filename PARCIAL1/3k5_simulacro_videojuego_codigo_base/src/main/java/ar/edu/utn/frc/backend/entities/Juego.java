
package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Juego {
    @Id
    private Long juegoId;
    private String nombre;
    private String genero;

    @OneToMany(mappedBy = "juego")
    private Set<Partida> partidas = new HashSet<>();

    // Constructors
    public Juego() {}

    public Juego(Long juegoId, String nombre, String genero) {
        this.juegoId = juegoId;
        this.nombre = nombre;
        this.genero = genero;
    }

    // Getters and Setters
    public Long getJuegoId() { return juegoId; }
    public void setJuegoId(Long juegoId) { this.juegoId = juegoId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Set<Partida> getPartidas() { return partidas; }
    public void setPartidas(Set<Partida> partidas) { this.partidas = partidas; }

    @Override
    public String toString() {
        return "Juego{" +
                "juegoId=" + juegoId +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }

}