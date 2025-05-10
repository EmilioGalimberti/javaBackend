package ar.edu.utn.frc.backend;

import ar.edu.utn.frc.backend.entities.Cliente;
import ar.edu.utn.frc.backend.services.ReservaService;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Paso 1: Crear el servicio y cargar las reservas
        ReservaService reservaService = new ReservaService();
        reservaService.cargarReservasDesdeCSV("reservas.csv");
        System.out.println("Reservas CARGADAS:");

        // Prueba paso 1
        System.out.println("Reservas:");
        reservaService.getReservas().forEach(System.out::println);

        System.out.println("\nClientes:");
        reservaService.getClientes().forEach(System.out::println);

        System.out.println("\nHabitaciones:");
        reservaService.getHabitaciones().forEach(System.out::println);

        // Paso 2: Calcular y mostrar el monto total pagado por clientes con reservas canceladas

        System.out.println("\nMontos totales pagados por clientes con reservas canceladas:");

        Map<Cliente, Double> montosCanceladas = reservaService.calcularMontoTotalPorClienteCanceladas();
        montosCanceladas.forEach((cliente, monto) ->
                System.out.println("Cliente: " + cliente.getNombre() + " | Monto total pagado: $" + monto)
        );
        //PUNTO 3 Generar reporte de reservas por tipo de habitación
        reservaService.generarReporteReservasPorTipo("reporte_reservas.csv");
        // PUNTO 4 Mostrar reservas confirmadas ordenadas por monto
        reservaService.mostrarReservasConfirmadasOrdenadasPorMonto();

        //punto 5 guardar en base de datos
        reservaService.guardarDatosEnBaseDeDatos();

    }
}