package ar.edu.utnfrc.backend.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="obras_artisticas")
@NamedQueries({
        @NamedQuery(name = "obra.findByName", query = "SELECT e FROM ObraArtistica e WHERE e.nombre = :nombre")
})
public class ObraArtistica implements Comparable<ObraArtistica>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="obras_artisticas_seq")
    private int id;

    @Basic
    private String nombre;

    // private int anio
    @Basic
    private String anio;

    @Column(name = "monto_asegurado")
    private double montoAsegurado;

    @Column(name = "seguro_total")
    private boolean seguroTotal; // < 0.5 false

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id", name = "museo_id")
    private Museo museo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id", name = "artista_id")
    private Autor artista;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id", name = "estilo_artistico_id")
    private EstiloArtistico estilo;

    public ObraArtistica() {
    }

    public ObraArtistica(String linea) {

        String[] tokens = linea.split(",");
        this.nombre = tokens[0];
        this.anio = tokens[1];
        this.montoAsegurado = Double.parseDouble(tokens[5]);
        this.seguroTotal = Double.parseDouble(tokens[6]) > 0.5;

        this.museo = new Museo(tokens[3]);
        this.artista = new Autor(tokens[2]);
        this.estilo = new EstiloArtistico(tokens[4]);

        this.museo.addObraArtistica(this);
        this.artista.addObra(this);
        this.estilo.addObra(this);
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

    public double getMontoAsegurado() {
        return montoAsegurado;
    }

    public void setMontoAsegurado(double montoAsegurado) {
        this.montoAsegurado = montoAsegurado;
    }

    public boolean isSeguroTotal() {
        return seguroTotal;
    }

    public void setSeguroTotal(boolean seguroTotal) {
        this.seguroTotal = seguroTotal;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNombreEstiloArtistico() {
        return this.estilo.getNombre();
    }

    public Museo getMuseo() {
        return this.museo;
    }

    public Autor getAutor() {
        return this.artista;
    }

    public EstiloArtistico getEstilo(){
        return this.estilo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObraArtistica that = (ObraArtistica) o;
        return id == that.id && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "ObraArtistica{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", montoAsegurado=" + montoAsegurado +
                ", seguroTotal=" + seguroTotal +
                ", museo=" + museo.getNombre() +
                ", autor=" + artista.getNombre() +
                ", estilo=" + estilo.getNombre() +
                '}';
    }

    @Override
    public int compareTo(ObraArtistica o) {
        // return this.anio - o.anio
        //return this.anio.compareTo(o.anio);
        return o.anio.compareTo(this.anio);
    }
}
