package org.example;

class Moto extends Vehiculo {
    public Moto(String marca) {
        super(marca);
    }

    @Override
    public void acelerar(int incremento) {
        if (incremento > 0) {
            setVelocidad(getVelocidad() + incremento * 2);
            System.out.println(getMarca() + " acelerando (Moto). Velocidad actual: " + getVelocidad() + " km/h");
        }
    }
}
