# Metodo para evitar id repetidos
```Java
/**
 * Obtiene el ID único del empleado asociado con el ID original y nombre dados.
 * Si el ID del empleado no existe en el sistema, crea uno nuevo, lo almacena,
 * y retorna el ID recién generado.
 * es decir si ya encuentra 1-Juan, simplemente lo retorna
 *  pero si encuentra 1-Pedro, le crea un nuevo ID
 *
 * @param originalId el identificador original del empleado, típicamente proporcionado externamente
 * @param nombre el nombre del empleado
 * @return el identificador único del empleado. Será un ID existente si el empleado
 * ya estaba registrado, o un nuevo ID generado si el empleado no se encontró.
 */
private Long getOrCreateEmployeeId(Long originalId, String nombre) {
    String key = originalId+"-"+nombre;

    // Si ya vimos este empleado antes, retornamos su ID
    if (empleadosIds.containsKey(key)) {
        return empleadosIds.get(key);
    }

    // Si es nuevo, le damos un nuevo ID
    Long nuevoId = proximoId;
    empleadosIds.put(key, nuevoId);
    proximoId++;

    return nuevoId;
}
```
**Primer caso**: Llega Juan por primera vez
- key = "1-Juan"
- ¿Existe "1-Juan" en empleadoKeyToId? NO
- ENTONCES crea nuevo ID (por ejemplo, 1)
- Guarda en el mapa: "1-Juan" → 1

**Segundo caso**: Llega Juan otra vez
- key = "1-Juan"
- ¿Existe "1-Juan" en empleadoKeyToId? SÍ
- NO crea nuevo ID
- Retorna el ID que ya tenía (1)

**Tercer caso**: Llega Pedro por primera vez
- key = "1-Pedro"
- ¿Existe "1-Pedro" en empleadoKeyToId? NO
- ENTONCES crea nuevo ID (por ejemplo, 2)
- Guarda en el mapa: "1-Pedro" → 2

La lógica es:
- Si NO existe → crear nuevo ID
- Si ya existe → usar el ID existente

Esto asegura que:
1. Cada combinación única de ID-nombre reciba un nuevo ID la primera vez
2. Si la misma persona aparece varias veces, siempre reciba el mismo ID

