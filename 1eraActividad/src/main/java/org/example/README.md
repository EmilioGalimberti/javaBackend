# Nombre del Proyecto

## Tabla de Contenidos

- [Instalación](#instalación)
- [Uso](#uso)
- [Documentación de las Clases](#Documentación)
- [Contribuir](#contribuir)


## Instalación

Sigue estos pasos para instalar el proyecto:

1. Clona el repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```
## Documentación

### Clase: `main` , Metodo Main

- **Descripción**: Método principal del programa que realiza pruebas y simulaciones de las clases definidas.
- **Ejemplo de uso**:
    - Instanciación de un `Coche`, `Moto`, `AutoElectrico` y `AutoDeportivo` para probar sus comportamientos.
    - Muestra en consola los resultados de las operaciones de acelerar, frenar, activar turbo (si aplica), y la
      interacción con la batería en el caso de vehículos eléctricos.

### Clase: `Vehiculo`

- **Descripción**: Clase base abstracta que proporciona las características y métodos comunes para todos los vehículos.
- **Métodos principales**:
    - `acelerar()`: Método abstracto que se implementará en las clases hijas para incrementar la velocidad.
    - `frenar()`: Método  que se implementará en las clases hijas para reducir la velocidad.
- **Características importantes**:
    - Contiene atributos comunes como `marca`, `modelo` y `velocidadActual`.

### Interfaz: `Electrico`

- **Descripción**: Define la funcionalidad que deben implementar los vehículos eléctricos.
- **Métodos principales**:
    - `cargarBateria()`: Método que debe recargar la batería al 100%.


### Clase: `Coche`

- **Descripción**: Representa un automóvil convencional con la capacidad de acelerar y frenar. hereda de Vehiculo
- **Métodos principales**:
    - `acelerar()`: Incrementa la velocidad actual del coche.
    - `frenar()`: Reduce la velocidad actual del coche.

### Clase: `Moto`

- **Descripción**: Modelo de motocicleta que posee métodos para acelerar y frenar. hereda de Vehiculo
- **Métodos principales**:
    - `acelerar()`: Incrementa la velocidad actual de la motocicleta.
    - `frenar()`: Disminuye la velocidad actual de la motocicleta.

### Clase: `AutoElectrico`

- **Descripción**: Automóvil eléctrico que combina la funcionalidad básica con la gestión de batería. hereda de Coche y implementa la interfaz Electrico
- **Métodos principales**:
    - `acelerar()`: Incrementa la velocidad y consume batería.
    - `frenar()`: Reduce la velocidad sin afectar la batería.
    - `cargarBateria()`: Recarga la batería al 100%.
- **Características importantes**:
    - Incluye un sistema que gestiona la batería restante y bloquea la aceleración en caso de batería descargada.

### Clase: `AutoDeportivo`

- **Descripción**: Automóvil deportivo que permite activar un modo turbo para mejorar el rendimiento. Hereda de Coche
- **Métodos principales**:
    - `acelerar()`: Incrementa la velocidad, con variaciones dependiendo si el turbo está activado o no.
    - `frenar()`: Reduce la velocidad actual.
    - `activarTurbo()`: Activa el modo turbo para mayor aceleración.

Cada clase está diseñada para representar diferentes tipos de vehículos, permitiendo la simulación de sus
comportamientos específicos en el contexto del proyecto.

## Uso
- Ejemplo de resultado:
  ```plaintext
    Prueba coche
    Toyota acelerando. Velocidad actual: 20 km/h
    Toyota frenando. Velocidad actual: 15 km/h
    ====================================================
    Prueba moto
    Yamaha acelerando (Moto). Velocidad actual: 30 km/h
    Yamaha frenando. Velocidad actual: 20 km/h
    ====================================================
    Prueba auto electrico
    Tesla acelerando. Velocidad actual: 120 km/h
    Tesla batería restante: 40%
    Tesla frenando. Velocidad actual: 70 km/h
    Tesla no puede acelerar 100 Bateria Descargada
    Tesla batería cargada al 100%.
    Tesla acelerando. Velocidad actual: 170 km/h
    Tesla batería restante: 50%
    ====================================================
    Prueba auto deportivo
    Ferrari acelerando (turbo desactivado). Velocidad actual: 20 km/h
    Ferrari frenando. Velocidad actual: 0 km/h
    Ferrari turbo activado
    Ferrari acelerando (turbo activado). Velocidad actual: 40 km/h
    
    Process finished with exit code 0
  


## Contribuir

Las contribuciones son bienvenidas. Sigue los pasos a continuación para contribuir:

1. Fork este repositorio.
2. Crea tu rama de características (`git checkout -b feature-nueva-funcionalidad`).
3. Confirma los cambios (`git commit -m 'Agregar nueva funcionalidad'`).
4. Sube la rama (`git push origin feature-nueva-funcionalidad`).
5. Crea un nuevo Pull Request.

