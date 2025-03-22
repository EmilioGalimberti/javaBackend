package org.example;

class AutoElectrico extends Coche implements Electrico {
    private int nivelBateria;

    //constructor
    public AutoElectrico(String marca) {
        super(marca);
        this.nivelBateria = 100;
    }

    @Override
    public void acelerar(int incremento) {
        if (nivelBateria > 0) {
            //hereda el metodo de Coche
            super.acelerar(incremento);
            nivelBateria -= 10;
            System.out.println(getMarca() + " batería restante: " + nivelBateria + "%");
        } else {
            System.out.println(getMarca() + " no puede acelerar. Batería descargada.");
        }
    }

    @Override
    public void cargarBateria() {
        nivelBateria = 100;
        System.out.println(getMarca() + " batería cargada al 100%.");
    }
}
