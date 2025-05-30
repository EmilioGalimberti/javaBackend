package com.example.Controller;

import com.example.Service.ReporteResponse;
import com.example.Service.ReportesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReportesController {
    private final ReportesService reportesService;

    @GetMapping("/incidentes")
    public ResponseEntity<ReporteResponse> getReporteIncidentes() {
        return ResponseEntity.ok(reportesService.generarReporteIncidentes());
    }

    @GetMapping("/incidentes/empleado/{idEmpleado}")
    public ResponseEntity<Resource> getReporteIncidentesEmpleado(@PathVariable Integer idEmpleado) {
        String archivo = reportesService.generarReporteIncidentesEmpleado(idEmpleado);
        return generarResponseArchivo(archivo, "reporte_incidentes_empleado_" + idEmpleado + ".txt");
    }

    @GetMapping("/pruebas/vehiculo/{idVehiculo}")
    public ResponseEntity<Resource> getReportePruebasVehiculo(@PathVariable Integer idVehiculo) {
        String archivo = reportesService.generarReporteDetallePruebasVehiculo(idVehiculo);
        return generarResponseArchivo(archivo, "reporte_pruebas_vehiculo_" + idVehiculo + ".txt");
    }


    private ResponseEntity<Resource> generarResponseArchivo(String rutaArchivo, String nombreArchivo) {
        try {
            Path archivo = Paths.get(rutaArchivo);
            Resource resource = new UrlResource(archivo.toUri());

            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + nombreArchivo + "\"")
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el archivo", e);
        }
    }
}