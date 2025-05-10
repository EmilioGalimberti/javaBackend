package ar.edu.utnfrc.backend.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "autores")
@NamedQueries({
        @NamedQuery(name = "autor.findByName", query = "SELECT e FROM Autor e WHERE e.nombre = :nombre"),
        @NamedQuery(name ="autor.findBuId", query="SELECT e FROM Autor e WHERE e.id = :id")
})
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "autores_seq")
    private int id;

    @Basic
    private String nombre;

    @OneToMany(mappedBy = "artista", fetch = FetchType.EAGER)
    private Set<ObraArtistica> obras;

    public Autor() {
    }

    public Autor(String nombre) {
        this.nombre = nombre;
        this.id = id;
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

    public void addObra(ObraArtistica obra) {
        if (this.obras == null) {
            this.obras = new HashSet<>();
        }
        this.obras.add(obra);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor artista = (Autor) o;
        return id == artista.id && Objects.equals(nombre, artista.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
