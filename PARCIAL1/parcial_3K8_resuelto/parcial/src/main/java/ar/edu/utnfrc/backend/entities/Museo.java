package ar.edu.utnfrc.backend.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "museos")
@NamedQueries({
        @NamedQuery(name = "museos.findByName", query = "SELECT M FROM Museo M WHERE M.nombre = :nombre"),
        @NamedQuery(name = "museos.findBuId", query = "SELECT e FROM Museo e WHERE e.id = :id")
})
public class Museo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "museos_seq")
    private int id;

    @Basic
    private String nombre;

    @OneToMany(mappedBy = "museo", fetch = FetchType.EAGER)
    private Set<ObraArtistica> obras;

    public Museo() {
    }

    public Museo(String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addObraArtistica(ObraArtistica obra) {
        if (obras == null) {
            obras = new HashSet<>();
        }
        this.obras.add(obra);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Museo museo = (Museo) o;
        return id == museo.id && Objects.equals(nombre, museo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Museo{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }

//    public void addAll(List<ObraArtistica> toBeMapped) {
//        this.obras.addAll(toBeMapped);
//    }
}
