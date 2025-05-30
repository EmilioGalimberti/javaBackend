package com.example.Service;


import com.example.model.Notificacion;
import com.example.model.Prueba;
import com.example.repository.NotificacionRepository;
import com.example.repository.PruebaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ReportesService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private PruebaRepository pruebaRepository;

    // Directorio donde se guardarán los reportes, configurable desde application.yml
    @Value("../reportes}")
    private String directorioReportes;

    // Formatos de fecha utilizados en los reportes
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter FORMATO_ARCHIVO = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");


    public ReporteResponse generarReporteIncidentes() {
        List<Prueba> pruebasConIncidentes = obtenerPruebasConIncidentes();
        String rutaArchivo = generarArchivoIncidentes(pruebasConIncidentes);

        return ReporteResponse.builder()
                .incidentes(pruebasConIncidentes)
                .rutaArchivo(rutaArchivo)
                .build();
    }

    private List<Prueba> obtenerPruebasConIncidentes() {
        return pruebaRepository.findPruebasConIncidentes();
    }

    private String generarArchivoIncidentes(List<Prueba> pruebas) {
        String nombreArchivo = String.format("incidentes_%s.txt",
                LocalDateTime.now().format(FORMATO_ARCHIVO));
        Path rutaCompleta = crearDirectorioYObtenerRuta(nombreArchivo);

        try (BufferedWriter writer = Files.newBufferedWriter(rutaCompleta)) {
            escribirEncabezadoReporte(writer, "REPORTE DE INCIDENTES");

            if (pruebas.isEmpty()) {
                writer.write("No se encontraron incidentes.\n");
                return rutaCompleta.toString();
            }

            for (Prueba prueba : pruebas) {
                escribirDetallePrueba(writer, prueba);
                escribirNotificacionesAsociadas(writer, prueba.getId());
                writer.write("\n----------------------------------------\n");
            }

            return rutaCompleta.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generando reporte de incidentes", e);
        }
    }

    public String generarReporteIncidentesEmpleado(Integer idEmpleado) {
        List<Prueba> pruebasEmpleado = pruebaRepository.findPruebasConIncidentesByEmpleado(idEmpleado);
        String nombreArchivo = String.format("incidentes_empleado_%d_%s.txt",
                idEmpleado, LocalDateTime.now().format(FORMATO_ARCHIVO));

        return generarReporteGenerico(pruebasEmpleado, nombreArchivo,
                String.format("REPORTE DE INCIDENTES - EMPLEADO %d", idEmpleado));
    }

    public String generarReporteDetallePruebasVehiculo(Integer idVehiculo) {
        List<Prueba> pruebasVehiculo = pruebaRepository.findByVehiculo_Id(idVehiculo);
        String nombreArchivo = String.format("pruebas_vehiculo_%d_%s.txt",
                idVehiculo, LocalDateTime.now().format(FORMATO_ARCHIVO));

        return generarReporteGenerico(pruebasVehiculo, nombreArchivo,
                String.format("REPORTE DE PRUEBAS - VEHÍCULO %d", idVehiculo));
    }

    private String generarReporteGenerico(List<Prueba> pruebas, String nombreArchivo, String titulo) {
        Path rutaCompleta = crearDirectorioYObtenerRuta(nombreArchivo);

        try (BufferedWriter writer = Files.newBufferedWriter(rutaCompleta)) {
            escribirEncabezadoReporte(writer, titulo);

            if (pruebas.isEmpty()) {
                writer.write("No se encontraron registros.\n");
                return rutaCompleta.toString();
            }

            for (Prueba prueba : pruebas) {
                escribirDetallePrueba(writer, prueba);
                escribirNotificacionesAsociadas(writer, (prueba.getId()));
                writer.write("\n----------------------------------------\n");
            }

            return rutaCompleta.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generando reporte", e);
        }
    }

    private Path crearDirectorioYObtenerRuta(String nombreArchivo) {
        try {
            Path directorio = Paths.get(directorioReportes);
            Files.createDirectories(directorio);
            return directorio.resolve(nombreArchivo);
        } catch (Exception e) {
            throw new RuntimeException("Error creando directorio de reportes", e);
        }
    }

    private void escribirEncabezadoReporte(BufferedWriter writer, String titulo) throws IOException, IOException {
        writer.write(titulo + "\n");
        writer.write("Fecha de generación: " +
                LocalDateTime.now().format(FORMATO_FECHA) + "\n\n");
    }

    private void escribirDetallePrueba(BufferedWriter writer, Prueba prueba) throws IOException {
        writer.write(String.format("=== Prueba ID: %d ===\nVehículo ID: %d\nInteresado ID: %d\nEmpleado ID: %d\nFecha Inicio: %s\nFecha Fin: %s\nComentarios: %s\n",
                prueba.getId(),
                prueba.getVehiculo().getId(),
                prueba.getInteresado().getId(),
                prueba.getEmpleado().getLegajo(),
                prueba.getFechaHoraInicio().format(FORMATO_FECHA),
                prueba.getFechaHoraFin().format(FORMATO_FECHA),
                prueba.getComentarios()));
    }

    private void escribirNotificacionesAsociadas(BufferedWriter writer, Integer idPrueba) throws IOException {
        List<Notificacion> notificaciones = notificacionRepository.findByPruebaId(idPrueba);

        if (!notificaciones.isEmpty()) {
            writer.write("\n--- Notificaciones Asociadas ---\n");
            for (Notificacion notificacion : notificaciones)
                writer.write(String.format("ID: %d\nMotivo: %s", notificacion.getId(), notificacion.getMotivo()));
        }
    }
}
