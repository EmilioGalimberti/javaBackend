package ar.edu.utn.frc.backend;

import ar.edu.utn.frc.backend.services.VideojuegoService;

public class Main {
    public static void main(String[] args) {
        VideojuegoService servicio = new VideojuegoService();

        try {
            // punto 1 Cargar datos del CSV
            servicio.cargarDatosDesdeCSV("partidas.csv");
            
            // Mostrar resumen de la carga
            servicio.mostrarResumenCarga();
            
            //  punto 2 Mostrar estadísticas de jugadores
            servicio.mostrarPromediosPorJugador();

            //punto 3 generar reporte de partidas por genero
            servicio.generarReporteGenero();

            //punto 4 mostrar top 10 partidas con mejores puntuaciones
            servicio.mostrarTop10Partidas();

            // punto 5 gurdar
            servicio.persistirDatos();

        } catch (RuntimeException e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
        }
    }
}