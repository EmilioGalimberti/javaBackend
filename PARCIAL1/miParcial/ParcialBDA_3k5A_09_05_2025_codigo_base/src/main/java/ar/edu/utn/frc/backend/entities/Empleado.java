
package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
public class Empleado {
    @Id
    private Long empleadoId;
    private String nombre;

    public Empleado() {
    }

    public Empleado(Long empleadoId, String nombre) {
        this.empleadoId = empleadoId;
        this.nombre = nombre;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Empleado empleado = (Empleado) o;
//        return Objects.equals(empleadoId, empleado.empleadoId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(empleadoId);
//    }

    @Override
    public String toString() {
        return "Empleado{" +
                "empleadoId=" + empleadoId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}