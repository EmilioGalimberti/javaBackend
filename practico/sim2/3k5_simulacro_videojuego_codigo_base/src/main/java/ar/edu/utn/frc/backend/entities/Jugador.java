package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Jugador {
    @Id
    private Long jugadorId;
    private String nombre;

    @OneToMany(mappedBy = "jugador")
    private Set<Partida> partidas = new HashSet<>();

    // Constructors
    public Jugador() {}

    public Jugador(Long jugadorId, String nombre) {
        this.jugadorId = jugadorId;
        this.nombre = nombre;
    }

    // Getters and Setters
    public Long getJugadorId() { return jugadorId; }
    public void setJugadorId(Long jugadorId) { this.jugadorId = jugadorId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<Partida> getPartidas() { return partidas; }
    public void setPartidas(Set<Partida> partidas) { this.partidas = partidas; }

    @Override
    public String toString() {
        return "Jugador{" +
                "jugadorId=" + jugadorId +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}