package ar.edu.utnfrc.backend.services;

import ar.edu.utnfrc.backend.entities.Autor;
import ar.edu.utnfrc.backend.entities.EstiloArtistico;
import ar.edu.utnfrc.backend.entities.Museo;
import ar.edu.utnfrc.backend.entities.ObraArtistica;
import ar.edu.utnfrc.backend.repositories.AutorRepositoryImpl;
import ar.edu.utnfrc.backend.repositories.EstiloArtisticoRepositoryImpl;
import ar.edu.utnfrc.backend.repositories.MuseoRepositoryImpl;
import ar.edu.utnfrc.backend.repositories.ObraArtisticaRepositoryImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ObraArtisticaService {

    private List<ObraArtistica> obras = new ArrayList<>();
    private final ObraArtisticaRepositoryImpl obraArtisticaRepository;
    private final EstiloArtisticoRepositoryImpl estiloArtisticoRepository;
    private final MuseoRepositoryImpl museoRepository;
    private final AutorRepositoryImpl autorRepository;


    //private double montoSeguroTotal;

    public ObraArtisticaService() {
        // montoSeguroTotal = 0;
        obraArtisticaRepository = new ObraArtisticaRepositoryImpl();
        estiloArtisticoRepository = new EstiloArtisticoRepositoryImpl();
        museoRepository = new MuseoRepositoryImpl();
        autorRepository = new AutorRepositoryImpl();
    }

    public void saveToDb() {
        // primero se graban los demas y se acutalizan los objetos porque una obra
        // esta relacionada con multiples combinaciones de museo autor y estilo, generando duplicados
        AtomicInteger i = new AtomicInteger();

        this.obras.forEach(obra -> {

            Museo m = museoRepository.findByName(obra.getMuseo().getNombre());
            Autor a = autorRepository.findByName(obra.getAutor().getNombre());
            EstiloArtistico e = estiloArtisticoRepository.findByName(obra.getEstilo().getNombre());

            if (m == null) {
                museoRepository.save(obra.getMuseo());
                m = museoRepository.findByName(obra.getMuseo().getNombre());
            }
            obra.getMuseo().setId(m.getId());


            if (a == null) {
                autorRepository.save(obra.getAutor());
                a = autorRepository.findByName(obra.getAutor().getNombre());
            }
            obra.getAutor().setId(a.getId());

            if (e == null) {
                estiloArtisticoRepository.save(obra.getEstilo());
                e = estiloArtisticoRepository.findByName(obra.getEstilo().getNombre());
            }
            obra.getEstilo().setId(e.getId());

            obraArtisticaRepository.save(obra);
        });
    }

    public boolean bulkInsert(URI location) throws IOException {
        this.obras = Files.lines(Paths.get(location)).skip(1).map(ObraArtistica::new).toList();
        return !this.obras.isEmpty();
    }

    private double getMontoPromedioAsegurado() {
        return this.obras.stream().mapToDouble(ObraArtistica::getMontoAsegurado).average().getAsDouble();
    }

    public List<ObraArtistica> getObrasSeguroParcialOrderByDescendingAnio() {
        double promedio = this.getMontoPromedioAsegurado();
        return this.obras.stream().filter(obra -> !obra.isSeguroTotal() && obra.getMontoAsegurado() > promedio).sorted((Comparator.comparing(ObraArtistica::getAnio)).reversed()).toList();

    }

    public void generarArchivoObrasPorEstiloArtistico() throws IOException {
        Map<String, AtomicLong> map = this.getObrasPorEstiloArtistico();
        FileWriter fw = new FileWriter("obras.txt");
        for (Map.Entry<String, AtomicLong> entry : map.entrySet()) {
            fw.append(entry.getKey()).append(",").append(entry.getValue().toString()).append("\n");
        }
        fw.flush();
        fw.close();
    }

    private Map<String, AtomicLong> getObrasPorEstiloArtistico() {

        Map<String, AtomicLong> map = new HashMap<>();
        this.obras.forEach(obra -> {
            String estilo = obra.getNombreEstiloArtistico();
            if (map.containsKey(estilo)) {
                map.get(estilo).getAndIncrement();
            } else {
                map.put(estilo, new AtomicLong());
            }
        });


        /*List<String> estilos = this.obras.stream()
                .map(ObraArtistica::getNombreEstiloArtistico)
                .distinct()
                .toList();

        HashMap<String, Long> map = new HashMap<>();
        estilos.forEach(estilo -> {
            long totalCount = this.obras.stream()
                    .filter(obra -> obra.getNombreEstiloArtistico().equalsIgnoreCase(estilo))
                    .count();
            map.put(estilo, totalCount);
        });*/

        return map;
    }

    public double getTotalMontoAseguradoSeguroTotal() {
        return this.obras.stream().filter(obra -> obra.isSeguroTotal()).mapToDouble(ObraArtistica::getMontoAsegurado).sum();

         /* List<ObraArtistica> aseguradas = this.obras.stream()
                .filter(obra -> obra.isSeguroTotal())
                .toList();

        double total = 0;
        for (ObraArtistica obra : aseguradas) {
            total += obra.getMontoAsegurado();
        }
        return total;

        this.obras.stream()
                .filter(obra -> obra.isSeguroTotal())
                .forEach(obra -> montoSeguroTotal += obra.getMontoAsegurado());*/
    }

    public double getTotalMontoSeguroParcial() {
        return this.obras.stream().filter(obra -> !obra.isSeguroTotal()).mapToDouble(ObraArtistica::getMontoAsegurado).sum();
    }

    public double getTotalAsegurado() {
        return this.obras.stream().mapToDouble(ObraArtistica::getMontoAsegurado).sum();
    }

}
