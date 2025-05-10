package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.entities.EstadoPartida;
import ar.edu.utn.frc.backend.entities.Juego;
import ar.edu.utn.frc.backend.entities.Jugador;
import ar.edu.utn.frc.backend.entities.Partida;
import ar.edu.utn.frc.backend.repositories.JuegoRepository;
import ar.edu.utn.frc.backend.repositories.JugadorRepository;
import ar.edu.utn.frc.backend.repositories.PartidaRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class VideojuegoService {
    // Estructuras de datos para mantener la información en memoria
    private final Map<Long, Jugador> jugadores;
    private final Map<Long, Juego> juegos;
    private final List<Partida> partidas;
    // Agregar estos campos en VideojuegoService
    private final JugadorRepository jugadorRepository;
    private final JuegoRepository juegoRepository;
    private final PartidaRepository partidaRepository;

    // Modificar el constructor
    public VideojuegoService() {
        this.jugadores = new HashMap<>();
        this.juegos = new HashMap<>();
        this.partidas = new ArrayList<>();
        this.jugadorRepository = new JugadorRepository();
        this.juegoRepository = new JuegoRepository();
        this.partidaRepository = new PartidaRepository();
    }

    // Paso 1: Cargar datos desde CSV
    public void cargarDatosDesdeCSV(String archivoCSV) {
        File archivo = new File(archivoCSV);

        try (Scanner scanner = new Scanner(archivo)) {
            // Saltar la cabecera
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");

                // Extraer datos
                Long partidaId = Long.parseLong(datos[0]);
                Long jugadorId = Long.parseLong(datos[1]);
                String jugadorNombre = datos[2];
                Long juegoId = Long.parseLong(datos[3]);
                String juegoNombre = datos[4];
                String genero = datos[5];
                LocalDate fechaPartida = LocalDate.parse(datos[6]);
                Integer duracionMinutos = Integer.parseInt(datos[7]);
                Integer puntuacion = Integer.parseInt(datos[8]);
// Antes de convertir el estado a enum, reemplaza espacios por guiones bajos
                EstadoPartida estado = EstadoPartida.valueOf(datos[9].toUpperCase().replace(" ", "_"));
                // Crear o recuperar jugador (asegurando unicidad)
                Jugador jugador = jugadores.computeIfAbsent(jugadorId,
                        id -> new Jugador(id, jugadorNombre));

                // Crear o recuperar juego (asegurando unicidad)
                Juego juego = juegos.computeIfAbsent(juegoId,
                        id -> new Juego(id, juegoNombre, genero));

                // Crear partida y establecer relaciones
                Partida partida = new Partida(partidaId, jugador, juego, fechaPartida,
                        duracionMinutos, puntuacion, estado);
                partidas.add(partida);

                // Actualizar relaciones bidireccionales
                jugador.getPartidas().add(partida);
                juego.getPartidas().add(partida);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
            throw new RuntimeException("No se pudo cargar el archivo CSV", e);
        }
    }

    // Métodos de acceso a los datos
    public List<Partida> getPartidas() {
        return new ArrayList<>(partidas);
    }

    public Collection<Jugador> getJugadores() {
        return jugadores.values();
    }

    public Collection<Juego> getJuegos() {
        return juegos.values();
    }

    // Métodos de conteo para verificación
    public int contarJugadoresUnicos() {
        return jugadores.size();
    }

    public int contarJuegosUnicos() {
        return juegos.size();
    }

    public int contarPartidasTotales() {
        return partidas.size();
    }

    // Método para mostrar resumen de carga
    public void mostrarResumenCarga() {
        System.out.println("\nResumen de Carga de Datos:");
        System.out.println("---------------------------");
        System.out.printf("Jugadores únicos: %d%n", contarJugadoresUnicos());
        System.out.printf("Juegos únicos: %d%n", contarJuegosUnicos());
        System.out.printf("Total de partidas: %d%n", contarPartidasTotales());
    }
// Agregar este método en VideojuegoService.java
public void mostrarPromediosPorJugador() {
    System.out.println("\nPromedio de puntuación por jugador (solo partidas completadas):");
    System.out.println("--------------------------------------------------------");
    
    for (Jugador jugador : jugadores.values()) {
        double sumaPuntuaciones = 0;
        int partidasCompletadas = 0;
        
        // Recorrer las partidas del jugador
        for (Partida partida : jugador.getPartidas()) {
            if (partida.getEstado() == EstadoPartida.COMPLETADA) {
                sumaPuntuaciones += partida.getPuntuacion();
                partidasCompletadas++;
            }
        }
        
        // Calcular y mostrar el promedio si tiene partidas completadas
        if (partidasCompletadas > 0) {
            double promedio = sumaPuntuaciones / partidasCompletadas;
            System.out.printf("%s: %.2f puntos (en %d partidas)%n", 
                jugador.getNombre(), 
                promedio, 
                partidasCompletadas);
        }
    }
}
// Agregar este método en VideojuegoService.java
public void generarReporteGenero() {
    // Map para contar partidas por género
    Map<String, Integer> partidasPorGenero = new HashMap<>();
    
    // Contar partidas por género
    for (Partida partida : partidas) {
        String genero = partida.getJuego().getGenero();
        partidasPorGenero.merge(genero, 1, Integer::sum);
    }
    
    // Escribir el archivo CSV
    try (FileWriter writer = new FileWriter("reporte_genero.csv")) {
        // Escribir encabezado
        writer.write("GENERO,TOTAL_PARTIDAS\n");
        
        // Escribir datos
        for (Map.Entry<String, Integer> entry : partidasPorGenero.entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
        
        System.out.println("\nReporte generado: reporte_genero.csv");
    } catch (IOException e) {
        System.err.println("Error al generar el reporte: " + e.getMessage());
        throw new RuntimeException("No se pudo generar el reporte", e);
    }
}
// Agregar este método en VideojuegoService.java
public void mostrarTop10Partidas() {
    System.out.println("\nTop 10 Partidas con Mejores Puntuaciones:");
    System.out.println("----------------------------------------");
    
    // Ordenar partidas por puntuación (descendente)
    List<Partida> partidasOrdenadas = new ArrayList<>(partidas);
    partidasOrdenadas.sort((p1, p2) -> p2.getPuntuacion().compareTo(p1.getPuntuacion()));
    
    // Mostrar las primeras 10 partidas
    for (int i = 0; i < Math.min(10, partidasOrdenadas.size()); i++) {
        Partida partida = partidasOrdenadas.get(i);
        System.out.printf("%d. %s - %s: %d puntos (%s)%n",
            i + 1,
            partida.getJugador().getNombre(),
            partida.getJuego().getNombre(),
            partida.getPuntuacion(),
            partida.getEstado());
    }
}
// Agregar este método para persistir los datos
public void persistirDatos() {
    System.out.println("\nPersistiendo datos en la base de datos...");
    
    // Primero persistir jugadores y juegos (entidades padres)
    for (Jugador jugador : jugadores.values()) {
        jugadorRepository.save(jugador);
    }
    
    for (Juego juego : juegos.values()) {
        juegoRepository.save(juego);
    }
    
    // Luego persistir partidas (entidad hija)
    for (Partida partida : partidas) {
        partidaRepository.save(partida);
    }
    
    System.out.println("Datos persistidos exitosamente.");
}
}