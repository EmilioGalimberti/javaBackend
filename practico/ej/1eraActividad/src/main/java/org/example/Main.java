package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Prueba coche");
        Coche coche = new Coche("Toyota");
        // Pruebas de aceleración y frenado
        coche.acelerar(20);
        coche.frenar(5);
        System.out.println("====================================================");

        //prueba Moto
        System.out.println("Prueba moto");
        Moto moto = new Moto("Yamaha");
        moto.acelerar(15);
        moto.frenar(10);
        System.out.println("====================================================");

        //prueba AutoElectrico
        System.out.println("Prueba auto electrico");
        AutoElectrico autoElectrico = new AutoElectrico("Tesla");
        autoElectrico.acelerar(120);
        autoElectrico.frenar(50);
        autoElectrico.acelerar(100);
        autoElectrico.cargarBateria();
        autoElectrico.acelerar(100);

        System.out.println("====================================================");

        System.out.println("Prueba auto deportivo");
        Deportivo deportivo = new Deportivo("Ferrari");
        deportivo.acelerar(20);
        deportivo.frenar(20);
        deportivo.activarTurbo(true);
        deportivo.acelerar(20);

    }
}