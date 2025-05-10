package ar.edu.utn.frc.backend.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
public class Curso {
    @Id
    private Long cursoId;
    private String nombre;
    private String areaTematica;
    private Integer duracionHoras;


    public Curso() {
    }

    public Curso(Long cursoId, String nombre, String areaTematica, Integer duracionHoras) {
        this.cursoId = cursoId;
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.duracionHoras = duracionHoras;
    }

    // Getters y Setters
    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public Integer getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(Integer duracionHoras) {
        this.duracionHoras = duracionHoras;
    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Curso curso = (Curso) o;
//        return Objects.equals(cursoId, curso.cursoId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(cursoId);
//    }

    @Override
    public String toString() {
        return "Curso{" +
                "cursoId=" + cursoId +
                ", nombre='" + nombre + '\'' +
                ", areaTematica='" + areaTematica + '\'' +
                ", duracionHoras=" + duracionHoras +
                '}';
    }
}