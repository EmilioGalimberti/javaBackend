
package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Partida {
    @Id
    private Long partidaId;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "juego_id")
    private Juego juego;

    private LocalDate fechaPartida;
    private Integer duracionMinutos;
    private Integer puntuacion;

    @Enumerated(EnumType.STRING)
    private EstadoPartida estado;

    // Constructors
    public Partida() {}

    public Partida(Long partidaId, Jugador jugador, Juego juego, LocalDate fechaPartida,
                   Integer duracionMinutos, Integer puntuacion, EstadoPartida estado) {
        this.partidaId = partidaId;
        this.jugador = jugador;
        this.juego = juego;
        this.fechaPartida = fechaPartida;
        this.duracionMinutos = duracionMinutos;
        this.puntuacion = puntuacion;
        this.estado = estado;
    }

    // Getters and Setters
    public Long getPartidaId() { return partidaId; }
    public void setPartidaId(Long partidaId) { this.partidaId = partidaId; }

    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }

    public Juego getJuego() { return juego; }
    public void setJuego(Juego juego) { this.juego = juego; }

    public LocalDate getFechaPartida() { return fechaPartida; }
    public void setFechaPartida(LocalDate fechaPartida) { this.fechaPartida = fechaPartida; }

    public Integer getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public Integer getPuntuacion() { return puntuacion; }
    public void setPuntuacion(Integer puntuacion) { this.puntuacion = puntuacion; }

    public EstadoPartida getEstado() { return estado; }
    public void setEstado(EstadoPartida estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Partida{" +
                "partidaId=" + partidaId +
                ", jugador=" + jugador.getNombre() +
                ", juego=" + juego.getNombre() +
                ", fechaPartida=" + fechaPartida +
                ", duracionMinutos=" + duracionMinutos +
                ", puntuacion=" + puntuacion +
                ", estado=" + estado +
                '}';
    }

}