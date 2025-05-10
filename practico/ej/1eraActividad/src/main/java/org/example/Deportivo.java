package org.example;

class Deportivo extends Coche {
    private boolean turboActivo;

    public Deportivo(String marca) {
        super(marca);
        this.turboActivo = false;
    }

    public void activarTurbo(boolean activar) {
        this.turboActivo = activar;
        String estado = activar ? "activado" : "desactivado";
        System.out.println(getMarca() + " turbo " + estado);
    }

    @Override
    public void acelerar(int incremento) {
        if (incremento > 0) {
            int factor = turboActivo ? 2 : 1;
            setVelocidad(getVelocidad() + incremento * factor);
            System.out.println(getMarca() + " acelerando (turbo " + (turboActivo ? "activado" : "desactivado") + "). Velocidad actual: " + getVelocidad() + " km/h");
        }
    }
}
