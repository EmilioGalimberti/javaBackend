package arrays.split;

//Enunciado:
//Se tiene una cadena de texto con datos de una persona, separados por comas: nombre,
//edad y altura.
//Escribir un programa que:
//1. Divida la cadena usando la coma como separador.
//2. Guarde cada parte en una variable del tipo correspondiente.
//3. Muestre en consola los datos con etiquetas, uno por línea.

public class main {
    public static void main(String[] args) {
        String datos = "Juan,25,1.75";
        String[] partes = datos.split(",");
        String nombre = partes[0];
        //pasar de string a entero
        int edad = Integer.parseInt(partes[1]);
        double altura = Double.parseDouble(partes[2]);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Altura: " + altura);
    }

}
