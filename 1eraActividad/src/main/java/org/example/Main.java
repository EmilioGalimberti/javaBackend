package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Prueba coche
        Coche coche = new Coche("Toyota");
        // Pruebas de aceleración y frenado
        coche.acelerar(20);
        coche.frenar(5);
    }
}