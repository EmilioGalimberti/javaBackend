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
        nivelBateria -= (incremento/2);
        if (nivelBateria > 0) {
            //hereda el metodo de Coche
            super.acelerar(incremento);
            //el get marca lo hereda de la clase Vehiculo
            System.out.println(getMarca() + " batería restante: " + nivelBateria + "%");
        } else {
            System.out.println(getMarca() + " no puede acelerar "+ incremento +" Bateria Descargada" );
        }
    }

    @Override
    public void cargarBateria() {
        nivelBateria = 100;
        System.out.println(getMarca() + " batería cargada al 100%.");
    }
}
