package agg.enCSV;

//Enunciado:
//Desarrollar un programa que permita al usuario:
//1. Cargar nuevos datos de personas desde el teclado (nombre, edad y altura).
//2. Guardar los datos ingresados en un archivo personas.csv.
//3. Leer ese archivo línea por línea.
//4. Procesar cada línea separándola con split(",").
//5. Mostrar un listado completo con el siguiente formato:
//Nombre: Ana - Edad: 23 - Altura: 1.60 m
//6. Luego, mostrar cuántas personas tienen más de 25 años y calcular la altura
//promedio.

//Conceptos:
//● Scanner para entrada por teclado y lectura de archivos.
//● Arrays (String[]) con split().
//● Integer.parseInt() y Double.parseDouble() para casting.
//● Archivos con File y PrintWriter.

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File archivo = new File("personas.csv");

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, true))) {
            System.out.print("¿Cuántas personas desea ingresar?: ");
            int cantidad = sc.nextInt();
            sc.nextLine(); // Limpia el buffer

            for (int i = 0; i < cantidad; i++) {
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Edad: ");
                int edad = sc.nextInt();
                System.out.print("Altura: ");
                double altura = sc.nextDouble();
                sc.nextLine(); // Limpia el buffer
                writer.println(nombre + "," + edad + "," + altura);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 2. Lectura y procesamiento del archivo
        int mayores25 = 0;
        double sumaAlturas = 0;
        int total = 0;
        try (Scanner lector = new Scanner(archivo)) {
            System.out.println("\nListado de personas:");
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]);
                double altura = Double.parseDouble(datos[2]);
                System.out.println("Nombre: " + nombre + " - Edad: "
                        + edad + " - Altura: " + altura + " m");
                if (edad > 25) mayores25++;
                sumaAlturas += altura;
                total++;
            }
            if (total > 0) {
                System.out.println("\nPersonas mayores de 25 años: "
                        + mayores25);
                System.out.printf("Altura promedio: %.2f m\n",
                        sumaAlturas / total);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        }
        sc.close();
    }
}