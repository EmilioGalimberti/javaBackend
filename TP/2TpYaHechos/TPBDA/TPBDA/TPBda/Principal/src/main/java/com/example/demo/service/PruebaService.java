package com.example.demo.service;

import com.example.demo.dtos.PruebaDTO;
import com.example.demo.model.Empleado;
import com.example.demo.model.Interesado;
import com.example.demo.model.Prueba;
import com.example.demo.model.Vehiculo;
import com.example.demo.repository.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PruebaService {

    @Autowired
    private InteresadoService interesadoService;

    @Autowired
    @Lazy
    private VehiculoService vehiculoService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private PruebaRepository pruebaRepository;

    public List<Prueba> findAll() {
        return (List<Prueba>) pruebaRepository.findAll();
    }

    public void crearPrueba(PruebaDTO pruebaDTO) {
        // Validar y obtener los datos necesarios desde los servicios
        Interesado interesado = interesadoService.findById(pruebaDTO.getIdInteresado());
        Vehiculo vehiculo = vehiculoService.findById(pruebaDTO.getIdVehiculo());
        Empleado empleado = empleadoService.findByLegajo(pruebaDTO.getIdEmpleado());

        // Crear la entidad Prueba
        Prueba prueba = new Prueba();
        prueba.setComentarios(" ");
        prueba.setFechaHoraFin(null);
        prueba.setFechaHoraInicio(LocalDateTime.now());
        prueba.setInteresado(interesado);
        prueba.setVehiculo(vehiculo);
        prueba.setEmpleado(empleado);

        // Guardar la prueba
        pruebaRepository.save(prueba);
    }

    public List<Prueba> obtenerPruebasEnCurso() {
        return pruebaRepository.findPruebasEnCurso();
    }

    public Prueba finalizarPrueba(Integer idPrueba, String comentario) {
        Prueba prueba = pruebaRepository.findById(idPrueba)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada con ID: " + idPrueba));

        prueba.setFechaHoraFin(LocalDateTime.now());  // Establece la fecha de fin como el momento actual
        prueba.setComentarios(comentario);  // Agrega el comentario

        return pruebaRepository.save(prueba);  // Guarda los cambios en la base de datos
    }
}
