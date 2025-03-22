# javaBackend

# Cosas para tener en cuenta:

## interfaz:

`Las interfaces en Java se utilizan para definir un contrato que las clases deben cumplir.Son un conjunto de métodos abstractos que una clase puede implementar, permitiendo heredar comportamientosde manera múltiple (a diferencia de las clases abstractas), y promoviendo una programación más flexible,desacoplada y orientada a interfaces. Por ejemplo, en el caso de vehículos eléctricos,podría haber una interfaz "Electrico" con un method para cargar baterías. */`
### ejemplo
`interface Electrico {`
`void cargarBateria();`
`}`
## clase abstracta:
`/*Abstract classes are used to define a base structure for other classes.They can have both complete methods with implementations and abstract methodsthat must be implemented by subclasses. This allows for shared behavioracross related classes while enforcing specific functionality in derived classes. */`
### ejemplo:

`abstract class Vehiculo`

## constructor:
`/*La palabra clave super se utiliza para llamar al constructor de la clase padre (en este caso, Vehiculo).Cuando creas una clase que hereda de otra, el constructor de la clase hija (Coche) necesita asegurarse de que la clase padre (Vehiculo) se inicialice correctamente.super(marca) llama al constructor de la clase Vehiculo que acepta un parámetro marca. */`

### ejemplo
`public Coche(String marca) {`
`super(marca);`
`}`

`// this. se usa para referirse al objeto actual. Sin this, Java no sabría si te estás refiriendo al atributo o al parámetro.`

## metodo abstractp:

`/*Un méthod abstracto es un méthod que no tiene implementación en la clase abstracta.Su propósito es obligar a las clases hijas a proporcionar una implementación específica.Esto permite definir un comportamiento que debe ser común en las subclases, pero cuya implementación puede variar entre ellas.*/`

### ejemplo

`public abstract void acelerar(int incremento);`