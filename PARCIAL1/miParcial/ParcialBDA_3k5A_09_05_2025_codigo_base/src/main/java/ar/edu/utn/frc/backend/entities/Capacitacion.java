
package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Capacitacion {
    @Id
    private Long capacitacionId;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private LocalDate fechaCapacitacion;
    private Integer calificacionFinal;
    private String estado;

    public Capacitacion() {
    }

    public Capacitacion(Long capacitacionId, Empleado empleado, Curso curso,
                        LocalDate fechaCapacitacion, Integer calificacionFinal, String estado) {
        this.capacitacionId = capacitacionId;
        this.empleado = empleado;
        this.curso = curso;
        this.fechaCapacitacion = fechaCapacitacion;
        this.calificacionFinal = calificacionFinal;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getCapacitacionId() {
        return capacitacionId;
    }

    public void setCapacitacionId(Long capacitacionId) {
        this.capacitacionId = capacitacionId;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getFechaCapacitacion() {
        return fechaCapacitacion;
    }

    public void setFechaCapacitacion(LocalDate fechaCapacitacion) {
        this.fechaCapacitacion = fechaCapacitacion;
    }

    public Integer getCalificacionFinal() {
        return calificacionFinal;
    }

    public void setCalificacionFinal(Integer calificacionFinal) {
        this.calificacionFinal = calificacionFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capacitacion that = (Capacitacion) o;
        return Objects.equals(capacitacionId, that.capacitacionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacitacionId);
    }

    @Override
    public String toString() {
        return "Capacitacion{" +
                "capacitacionId=" + capacitacionId +
                ", empleado=" + empleado.getNombre() +
                ", curso=" + curso.getNombre() +
                ", fechaCapacitacion=" + fechaCapacitacion +
                ", calificacionFinal=" + calificacionFinal +
                ", estado='" + estado + '\'' +
                '}';
    }
}