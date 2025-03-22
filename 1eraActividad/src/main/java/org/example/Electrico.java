package org.example;

/*
Las interfaces en Java se utilizan para definir un contrato que las clases deben cumplir.
Son un conjunto de métodos abstractos que una clase puede implementar, permitiendo heredar comportamientos
de manera múltiple (a diferencia de las clases abstractas), y promoviendo una programación más flexible,
desacoplada y orientada a interfaces. Por ejemplo, en el caso de vehículos eléctricos,
podría haber una interfaz "Electrico" con un method para cargar baterías.
 */
interface Electrico {
    void cargarBateria();
}