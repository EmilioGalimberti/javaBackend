package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

//agg anotaciones jpa
@Entity
@Table(name = "Reserva")
public class Reserva {
    @Id
    private int id;
    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Habitacion habitacion;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double totalPagado;
    private String estado;

    public Reserva(int id, Cliente cliente, Habitacion habitacion, LocalDate fechaInicio,
                   LocalDate fechaFin, double totalPagado, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalPagado = totalPagado;
        this.estado = estado;
    }

    public Reserva() {

    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Reserva{id=" + id + ", cliente=" + cliente + ", habitacion=" + habitacion +
                ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin +
                ", totalPagado=" + totalPagado + ", estado='" + estado + "'}";
    }
}