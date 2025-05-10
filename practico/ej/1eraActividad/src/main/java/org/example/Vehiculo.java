package org.example;

/*
Abstract classes are used to define a base structure for other classes.
They can have both complete methods with implementations and abstract methods
that must be implemented by subclasses. This allows for shared behavior
across related classes while enforcing specific functionality in derived classes.
 */

abstract class Vehiculo {
    //Atributos
    private final String marca;
    private int velocidad;

    //Constructor
    public Vehiculo(String marca) {
        this.marca = marca;  // this se usa para referirse al objeto actual. Sin this, Java no sabría si te estás refiriendo al atributo o al parámetro.
        this.velocidad = 0;
    }

    //getters
    public String getMarca() {
        return marca;
    }

    public int getVelocidad() {
        return velocidad;
    }
    //seter
    protected void setVelocidad(int nuevaVelocidad) {
        velocidad = nuevaVelocidad;
    }

    public void frenar(int decremento) {
        if (decremento > 0) {
            velocidad = Math.max(velocidad - decremento, 0);
            System.out.println(marca + " frenando. Velocidad actual: " + velocidad + " km/h");
        }
    }

/*
Un méthod abstracto es un méthod que no tiene implementación en la clase abstracta.
Su propósito es obligar a las clases hijas a proporcionar una implementación específica.
Esto permite definir un comportamiento que debe ser común en las subclases, pero cuya 
implementación puede variar entre ellas.
*/

    public abstract void acelerar(int incremento);
}

