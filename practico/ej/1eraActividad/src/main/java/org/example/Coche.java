package org.example;

class Coche extends Vehiculo {
    //constructor
    public Coche(String marca) {
        /*
        La palabra clave super se utiliza para llamar al constructor de la clase padre (en este caso, Vehiculo).
        Cuando creas una clase que hereda de otra, el constructor de la clase hija (Coche) necesita asegurarse de que la clase padre (Vehiculo) se inicialice correctamente.
        super(marca) llama al constructor de la clase Vehiculo que acepta un parámetro marca.
         */
        super(marca);
    }

    @Override
    public void acelerar(int incremento) {
        if (incremento > 0) {
            setVelocidad(getVelocidad() + incremento);
            System.out.println(getMarca() + " acelerando. Velocidad actual: " + getVelocidad() + " km/h");
        }
    }
}

