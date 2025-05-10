package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.entities.Capacitacion;
import ar.edu.utn.frc.backend.entities.Curso;
import ar.edu.utn.frc.backend.entities.Empleado;
import ar.edu.utn.frc.backend.repositories.CapacitacionRepository;
import ar.edu.utn.frc.backend.repositories.CursoRepository;
import ar.edu.utn.frc.backend.repositories.EmpleadoRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class CapacitacionesService {
    private EmpleadoRepository empleadoRepository = new EmpleadoRepository();
    private CursoRepository cursoRepository = new CursoRepository();
    private CapacitacionRepository capacitacionRepository = new CapacitacionRepository();

    //- Almacena pares clave-valor
    // no matiene orden
    //- Usa cuando necesitas buscar elementos por una clave específica `HashMap`
    private Map<Long, Curso> cursos = new HashMap<>();
    private Map<Long, Empleado> empleados = new HashMap<>();
    //- Almacena elementos en secuencia
    //- Ideal para listas secuenciales
    //- - Usa cuando necesitas una lista ordenada y acceso secuencial `ArrayList`
    //- No hay necesidad de una estructura clave-valor
    private List<Capacitacion> capacitaciones = new ArrayList<>();

    //Map para manejar las claves unicas de empleados.
    private Map<String, Long> empleadosIds = new HashMap<>();    //El sufijo `L` en `1L` indica que el número es de tipo `long` en Java
    private Long proximoId = 1L;

    // PUNTO 1 CARGAR DATOS DESDE EL CSV
    public void cargarDatos(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            // Saltar la primera línea (encabezados)
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(",");

                // Procesar Empleado
                Long originalEmployeeId = Long.parseLong(datos[1]);
                String employeeName = datos[2];
                // aca llamo al metodo getOrCreateEmployeeId para verificar si ya existe el empleado con ese id
                Long newEmployeeId = getOrCreateEmployeeId(originalEmployeeId, employeeName);

                Empleado empleado = new Empleado(newEmployeeId, employeeName);
                empleados.put(newEmployeeId, empleado);

                // Procesar Curso
                Long cursoId = Long.parseLong(datos[3]);
                if (!cursos.containsKey(cursoId)) {
                    Curso curso = new Curso(cursoId, datos[4], datos[5], Integer.parseInt(datos[7]));
                    cursos.put(cursoId, curso);
                }

                // Crear Capacitación
                Capacitacion capacitacion = new Capacitacion(
                        Long.parseLong(datos[0]),
                        empleado,
                        cursos.get(cursoId),
                        LocalDate.parse(datos[6]),
                        Integer.parseInt(datos[8]),
                        datos[9]
                );

                capacitaciones.add(capacitacion);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Obtiene el ID único del empleado asociado con el ID original y nombre dados.
     * Si el ID del empleado no existe en el sistema, crea uno nuevo, lo almacena,
     * y retorna el ID recién generado.
     * es decir si ya encuentra 1-Juan, simplemente lo retorna
     *  pero si encuentra 1-Pedro, le crea un nuevo ID
     *
     * @param originalId el identificador original del empleado, típicamente proporcionado externamente
     * @param nombre el nombre del empleado
     * @return el identificador único del empleado. Será un ID existente si el empleado
     * ya estaba registrado, o un nuevo ID generado si el empleado no se encontró.
     */
    private Long getOrCreateEmployeeId(Long originalId, String nombre) {
        String key = originalId+"-"+nombre;

        // Si ya vimos este empleado antes, retornamos su ID
        if (empleadosIds.containsKey(key)) {
            return empleadosIds.get(key);
        }

        // Si es nuevo, le damos un nuevo ID
        Long nuevoId = proximoId;
        empleadosIds.put(key, nuevoId);
        proximoId++;

        return nuevoId;
    }

    // MOSTRAR DATOS CARGADOS PARA VERIFICAR
    public void mostrarDatos() {
        System.out.println("\n=== EMPLEADOS CARGADOS ===");
        for (Empleado empleado : empleados.values()) {
            System.out.println("ID: " + empleado.getEmpleadoId() +
                    ", Nombre: " + empleado.getNombre());
        }

        System.out.println("\n=== CURSOS CARGADOS ===");
        for (Curso curso : cursos.values()) {
            System.out.println("ID: " + curso.getCursoId() +
                    ", Nombre: " + curso.getNombre() +
                    ", Área: " + curso.getAreaTematica() +
                    ", Duración: " + curso.getDuracionHoras() + "h");
        }

        System.out.println("\n=== CAPACITACIONES CARGADAS ===");
        for (Capacitacion cap : capacitaciones) {
            System.out.println("ID: " + cap.getCapacitacionId() +
                    ", Empleado: " + cap.getEmpleado().getNombre() +
                    ", Curso: " + cap.getCurso().getNombre() +
                    ", Fecha: " + cap.getFechaCapacitacion() +
                    ", Calificación: " + cap.getCalificacionFinal() +
                    ", Estado: " + cap.getEstado());
        }
    }

    //La verdad que no hay que calcular ningun promedio porque ninguno se repite
    /**
     * PUNTO 2
     * Calcula y muestra la calificación promedio de cada empleado en cursos del área de Tecnología.
     * Solo considera las capacitaciones del área "Tecnología".
     */
    public void mostrarPromediosTecnologia() {
        // Map para almacenar la suma de calificaciones y cantidad de cursos por empleado
        Map<Long, Integer> sumaCalificaciones = new HashMap<>();
        Map<Long, Integer> cantidadCursos = new HashMap<>();

        // Recorremos todas las capacitaciones
        for (Capacitacion cap : capacitaciones) {
            // Verificamos si el curso es del área de Tecnología
            if ("Tecnología".equals(cap.getCurso().getAreaTematica())) {
                Long empleadoId = cap.getEmpleado().getEmpleadoId();
            
                // Sumamos la calificación
                sumaCalificaciones.merge(empleadoId, cap.getCalificacionFinal(), Integer::sum);
                // Incrementamos el contador de cursos
                cantidadCursos.merge(empleadoId, 1, Integer::sum);
            }
        }

        // Mostramos los resultados
        System.out.println("\n=== PROMEDIOS EN CURSOS DE TECNOLOGÍA POR EMPLEADO ===");
        // sumaCalificaciones.keySet(), Devuelve todos los IDs de los empleados que han tomado cursos de Tecnología.
        for (Long empleadoId : sumaCalificaciones.keySet()) {
            Empleado empleado = empleados.get(empleadoId);
            //El `(double)` es un cast o conversión explícita
            double promedio = (sumaCalificaciones.get(empleadoId) / (double) cantidadCursos.get(empleadoId));
        
            System.out.printf("Empleado: %s (ID: %d) - Promedio: %.2f%n", 
                empleado.getNombre(), 
                empleado.getEmpleadoId(), 
                promedio);
        }
    }

    /**
     * PUNTO 3
     * Genera un archivo CSV con el reporte de la cantidad de capacitaciones por área temática.
     */
    public void generarReporteAreas() {
            // Map para contar capacitaciones por área
            Map<String, Integer> capacitacionesPorArea = new HashMap<>();

            // Contar capacitaciones por área
            for (Capacitacion cap : capacitaciones) {
                String area = cap.getCurso().getAreaTematica();

                //1. Si el área no existe en el map, agrega el área con valor 1
                //2. Si el área ya existe, suma 1 al valor actual
                capacitacionesPorArea.merge(area, 1, Integer::sum);
            }

            // Generar archivo CSV
            try (PrintWriter writer = new PrintWriter("reporte_areas.csv")) {
                // Escribir encabezados
                writer.println("Area_Tematica,Cantidad_Capacitaciones");

                // Escribir datos
                //Map.Entry te permite trabajar con ambos valores (clave y valor) al mismo tiempo cuando estás recorriendo un Map `Map.Entry`
                for (Map.Entry<String, Integer> registro : capacitacionesPorArea.entrySet()) {
                    String area = registro.getKey();
                    int cantidad = registro.getValue();
                    writer.println(area + "," + cantidad);
                }

                System.out.println("Reporte generado exitosamente en 'reporte_areas.csv'");
            } catch (FileNotFoundException e) {
                System.err.println("Error al generar el reporte: " + e.getMessage());
            }
            }
    /**
     * PUNTO 4
     * Muestra en consola el top 5 de capacitaciones con mejor calificación final.
     */
    public void mostrarTopCapacitaciones() {
        // Creamos una lista de las capacitaciones para poder ordenarla
        List<Capacitacion> capacitacionesOrdenadas = new ArrayList<>(capacitaciones);

        // Ordenamos la lista por calificación final de mayor a menor
        capacitacionesOrdenadas.sort((c1, c2) ->
            c2.getCalificacionFinal().compareTo(c1.getCalificacionFinal())
        );

        // Mostramos el top 5
        System.out.println("\n=== TOP 5 CAPACITACIONES CON MEJOR CALIFICACIÓN ===");

        // Tomamos solo los primeros 5 elementos (o menos si no hay suficientes)

        for (int i = 0; i < Math.min(5, capacitacionesOrdenadas.size()); i++) {
            Capacitacion cap = capacitacionesOrdenadas.get(i);
            System.out.println(i+1 + "* ID CAPCITACION: "+ cap.getCapacitacionId() + ". Nombre Curso " + cap.getCurso().getNombre() + " - " + cap.getCalificacionFinal() + " puntos");
        }
    }

    /**
     * PUNTO 5
     * Guarda todos los datos cargados en la base de datos.
     */
    public void guardarEnBaseDeDatos() {
        try {
            // Primero guardamos los empleados
            System.out.println("Guardando empleados...");
            for (Empleado empleado : empleados.values()) {
                empleadoRepository.save(empleado);
            }

            // Luego los cursos
            System.out.println("Guardando cursos...");
            for (Curso curso : cursos.values()) {
                cursoRepository.save(curso);
            }

            // Finalmente las capacitaciones
            System.out.println("Guardando capacitaciones...");
            for (Capacitacion capacitacion : capacitaciones) {
                capacitacionRepository.save(capacitacion);
            }

            System.out.println("Todos los datos han sido guardados exitosamente en la base de datos.");
        } catch (Exception e) {
            System.err.println("Error al guardar en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    }