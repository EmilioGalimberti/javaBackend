package lectura.archivo.csv;

//3. Lectura de archivos CSV
//Enunciado:
//Escribir un programa que:
//1. Lea el archivo datos.csv línea por línea.
//2. Separe cada línea utilizando la coma como separador.
//3. Convierta los datos al tipo correspondiente (String, int, double).
//4. Muestre en consola un mensaje con el formato:

//[Nombre] tiene [Edad] años y mide [Altura] m.

//Ejemplo de archivo datos.csv:
//Ana,23,1.60
//Luis,30,1.80
//María,28,1.65

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            File archivo = new File("D:\\OneDrive - frc.utn.edu.ar\\UTN\\3er año\\2025\\back\\repoBack\\javaBackend\\1eraActividad\\actividades1eerParcial\\src\\main\\java\\lectura\\archivo\\csv\\datos.csv");
            Scanner lector = new Scanner(archivo);

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]);
                double altura = Double.parseDouble(datos[2]);
                System.out.println(nombre + " tiene " + edad + " años y mide " + altura + " m.");
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("No existe el archivo");
        }
    }
}
