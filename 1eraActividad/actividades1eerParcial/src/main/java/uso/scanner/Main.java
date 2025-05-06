package uso.scanner;

import java.util.Scanner;

//Se desea escribir un programa en Java que pida al usuario ingresar su nombre y su edad
//utilizando el teclado, y luego muestre un saludo personalizado con esa información.
//Crear un programa que:
//1. Solicite al usuario su nombre (como texto).
//2. Solicite su edad (como número entero).
//3. Muestre un mensaje de saludo en pantalla con los datos ingresados.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the file: ");
        String filename = scanner.nextLine();

        System.out.println("Enter the size of the file: ");
        int size = scanner.nextInt();
        System.out.println("nombre "+ filename + " tamaño "+ size);
        scanner.close();
    }
}