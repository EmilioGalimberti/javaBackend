package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.entities.Cliente;
import ar.edu.utn.frc.backend.entities.Habitacion;
import ar.edu.utn.frc.backend.entities.Reserva;
import ar.edu.utn.frc.backend.repositories.ClienteRepository;
import ar.edu.utn.frc.backend.repositories.HabitacionRepository;
import ar.edu.utn.frc.backend.repositories.ReservaRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class ReservaService {
    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final HabitacionRepository habitacionRepository = new HabitacionRepository();
    private final ReservaRepository reservaRepository = new ReservaRepository();


    private final Map<Integer, Cliente> clientes = new HashMap<>();
    private final Map<Integer, Habitacion> habitaciones = new HashMap<>();
    private final List<Reserva> reservas = new ArrayList<>();

    //paso 1
    public void cargarReservasDesdeCSV(String archivoCSV) {
        File archivo = new File(archivoCSV);

        try (Scanner scanner = new Scanner(archivo)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Omitir la cabecera
            }

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");

                int reservaId = Integer.parseInt(datos[0]);
                int clienteId = Integer.parseInt(datos[1]);
                String clienteNombre = datos[2];
                int habitacionId = Integer.parseInt(datos[3]);
                String habitacionTipo = datos[4];
                LocalDate fechaInicio = LocalDate.parse(datos[5]);
                LocalDate fechaFin = LocalDate.parse(datos[6]);
                double totalPagado = Double.parseDouble(datos[7]);
                String estado = datos[8];

                // Crear o recuperar cliente
                // `ComputeIfAbsent` es un método de Map que:
                //1. Busca un valor en el mapa usando la clave proporcionada
                //2. Si el valor no existe, ejecuta la función que le pasamos para crear un nuevo valor
                //3. Almacena el nuevo valor en el mapa y lo retorna
                //4. Si el valor ya existía, simplemente lo retorna
                Cliente cliente = clientes.computeIfAbsent(clienteId, id -> new Cliente(clienteId, clienteNombre));

                // Crear o recuperar habitación
                Habitacion habitacion = habitaciones.computeIfAbsent(habitacionId, id -> new Habitacion(habitacionId, habitacionTipo));

                // Crear reserva
                Reserva reserva = new Reserva(reservaId, cliente, habitacion, fechaInicio, fechaFin, totalPagado, estado);
                reservas.add(reserva);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Collection<Cliente> getClientes() {
        return clientes.values();
    }

    public Collection<Habitacion> getHabitaciones() {
        return habitaciones.values();
    }

    //PASO 2
    public Map<Cliente, Double> calcularMontoTotalPorClienteCanceladas() {
        Map<Cliente, Double> totalPorCliente = new HashMap<>();

        for (Reserva reserva : reservas) {
            if ("Cancelada".equalsIgnoreCase(reserva.getEstado())) { // Solo procesar reservas "Canceladas"
                Cliente cliente = reserva.getCliente();
                double totalPagado = reserva.getTotalPagado();

                // Sumar el monto pagado para este cliente
                //El método `merge` en Java es una operación sobre
                // Map que combina el valor actual con un nuevo valor.
                // Es una forma concisa de actualizar un valor en el mapa evitando tener que hacer comprobaciones explícitas
                totalPorCliente.merge(cliente, totalPagado, Double::sum);
            }
        }

        return totalPorCliente;
    }
    //Paso 3
    public void generarReporteReservasPorTipo(String nombreArchivo) {
        Map<String, Integer> reservasPorTipo = new HashMap<>();

        // Contar reservas por tipo de habitación
        for (Reserva reserva : reservas) {
            String tipoHabitacion = reserva.getHabitacion().getTipo();
            reservasPorTipo.merge(tipoHabitacion, 1, Integer::sum);
        }

        // Escribir el reporte en un archivo CSV
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            // Escribir cabecera
            writer.write("TIPO_HABITACION,CANTIDAD_RESERVAS\n");

            // Escribir datos
            for (Map.Entry<String, Integer> entrada : reservasPorTipo.entrySet()) {
                writer.write(String.format("%s,%d\n",
                    entrada.getKey(),
                    entrada.getValue()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Paso4
    public void mostrarReservasConfirmadasOrdenadasPorMonto() {
        // Filtrar solo las reservas confirmadas y ordenarlas
        List<Reserva> reservasConfirmadas = reservas.stream()
            .filter(r -> "Confirmada".equalsIgnoreCase(r.getEstado()))
            .sorted((r1, r2) -> Double.compare(r2.getTotalPagado(), r1.getTotalPagado()))
            .toList();

        // Mostrar las reservas
        System.out.println("\nReservas Confirmadas (ordenadas por monto descendente):");
        System.out.println("----------------------------------------------------");
        for (Reserva reserva : reservasConfirmadas) {
            System.out.printf("Cliente: %-20s | Habitación: %-10s | Monto: $%.2f%n",
                reserva.getCliente().getNombre(),
                reserva.getHabitacion().getTipo(),
                reserva.getTotalPagado());
        }
    }
    //paso5
    // Después de cargar en memoria, persistir en la base de datos
    public void guardarDatosEnBaseDeDatos() {
        for (Cliente cliente : clientes.values()) {
            clienteRepository.save(cliente);
        }

        for (Habitacion habitacion : habitaciones.values()) {
            habitacionRepository.save(habitacion);
        }

        for (Reserva reserva : reservas) {
            reservaRepository.save(reserva);
        }
        System.out.println("\nDatos En Base de Datos:");
    }
}