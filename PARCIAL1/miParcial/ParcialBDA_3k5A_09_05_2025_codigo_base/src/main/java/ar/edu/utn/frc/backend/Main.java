package ar.edu.utn.frc.backend;

import ar.edu.utn.frc.backend.services.CapacitacionesService;

public class Main {
    public static void main(String[] args) {;
        CapacitacionesService service = new CapacitacionesService();
        try {
            // Punto 1 Cargar datos desde el CSV
            service.cargarDatos("capacitaciones.csv");
            service.mostrarDatos();
            // Punto 2 calcular promedio de emepleado para area tecnologia
            service.mostrarPromediosTecnologia();
            // Punto 3 escribir csv con área temática y la cantidad total de capacitaciones registradas
            service.generarReporteAreas();
            // Punto 4 mostrar las top 5 mejores capacitaciones segun su nota final
            service.mostrarTopCapacitaciones();
            //PUNTO 5 guardar los datos
            service.guardarEnBaseDeDatos();
        } catch (Exception e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            e.printStackTrace();
        }

    }
}


