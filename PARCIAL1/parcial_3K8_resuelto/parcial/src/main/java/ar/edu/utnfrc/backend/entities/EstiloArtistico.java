package ar.edu.utnfrc.backend.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="estilo_artisticos")
@NamedQueries({
        @NamedQuery(name = "estilos.findByName", query = "SELECT e FROM EstiloArtistico e WHERE e.nombre = :nombre"),
        @NamedQuery(name ="estilos.findBuId", query="SELECT e FROM EstiloArtistico e WHERE e.id = :id")
})
public class EstiloArtistico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "estilo_artisticos_seq")
    private int id;

    @Basic
    private String nombre;

    @OneToMany(mappedBy = "estilo", fetch = FetchType.EAGER)
    private Set<ObraArtistica> obras;

    public EstiloArtistico() {}

    public EstiloArtistico(String nombre) {
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
        if(this.obras == null) {
            this.obras = new HashSet<>();
        }
        this.obras.add(obra);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstiloArtistico that = (EstiloArtistico) o;
        return id == that.id && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "EstiloArtistico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
//
//    public void addAll(List<ObraArtistica> toBeMapped) {
//        this.obras.addAll(toBeMapped);
//    }
}
