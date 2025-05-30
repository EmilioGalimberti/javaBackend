package com.example.demo.service;

import com.example.demo.model.Notificacion;
import com.example.demo.model.Posicion;
import com.example.demo.model.Vehiculo;
import com.example.demo.model.apiModel.Configuracion;
import com.example.demo.model.apiModel.ZonaRestringida;
import com.example.demo.repository.NotificacionRepository;
import com.example.demo.repository.PosicionRepository;
import com.example.demo.repository.PruebaRepository;
import com.example.demo.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private ConfiguracionService configuracionService;

    @Autowired
    private PosicionRepository posicionRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private PruebaRepository pruebaRepository;


    public Vehiculo findById(Integer idVehiculo) {
        return vehiculoRepository.findById(idVehiculo).orElse(null);
    }

    public Posicion obtenerUltimaPosicion(Integer idVehiculo) {
        // Aquí haces una consulta para obtener la última posición registrada del vehículo.
        return posicionRepository.findFirstByVehiculoIdOrderByFechaHoraDesc(idVehiculo);
    }

    public boolean verificarPosicion(Posicion posicion, Integer idVehiculo) {

        // Verificar si el vehículo tiene una prueba activa
        boolean pruebaActiva = pruebaRepository.existePruebaActiva(idVehiculo);
        if (!pruebaActiva) {
            return false;
        }


        Configuracion configuracion = configuracionService.obtenerConfiguracion();

        // Verificar si el vehículo está dentro del radio permitido
        if (!estaDentroDelRadio(posicion, configuracion)) {
            // Si está fuera del radio, disparar la acción correspondiente
            dispararNotificacion(1);
        }

        // Verificar si el vehículo está dentro de una zona restringida
        if (estaDentroDeZonaRestringida(posicion, configuracion)) {
            // Si está dentro de una zona restringida, disparar la acción correspondiente
            dispararNotificacion(2);
        }

        return true;
    }

    private boolean estaDentroDelRadio(Posicion posicion, Configuracion configuracion) {
        // Calcula la distancia entre la posición del vehículo y la agencia
        double distancia = calcularDistancia(posicion.getLatitud(), posicion.getLongitud(),
                configuracion.getCoordenadasAgencia().getLat(),
                configuracion.getCoordenadasAgencia().getLon());

        // Comparar si está dentro del radio admitido
        return distancia <= configuracion.getRadioAdmitidoKm();
    }

    private boolean estaDentroDeZonaRestringida(Posicion posicion, Configuracion configuracion) {
        for (ZonaRestringida zona : configuracion.getZonasRestringidas()) {
            if (estaDentroDeZona(posicion, zona)) {
                return true; // El vehículo está dentro de una zona restringida
            }
        }
        return false;
    }

    private boolean estaDentroDeZona(Posicion posicion, ZonaRestringida zona) {
        // Verificar si la posición está dentro de los límites de la zona
        return posicion.getLatitud() >= zona.getNoroeste().getLat() &&
                posicion.getLatitud() <= zona.getSureste().getLat() &&
                posicion.getLongitud() >= zona.getNoroeste().getLon() &&
                posicion.getLongitud() <= zona.getSureste().getLon();
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Implementar cálculo de distancia Euclidiana entre las coordenadas
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }

    private void dispararNotificacion(int motivo) {
        String texto = "";
        if (motivo == 1) {
            texto = "Fuera de posicion";
        } else if (motivo == 2) {
            texto = "Dentro de zona restringida";
        }
        Notificacion notificacion = new Notificacion();
        notificacion.setMotivo(texto);
        notificacionRepository.save(notificacion);
    }

}