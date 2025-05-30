package com.example.demo.service;

import com.example.demo.model.apiModel.Configuracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfiguracionService {


    private final RestTemplate restTemplate;

    @Autowired @Lazy
    public ConfiguracionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    public RestTemplate ConfrestTemplate() {
        return new RestTemplate();
    }

    public Configuracion obtenerConfiguracion() {
        String apiUrl = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/"; // La URL de la API que devuelve la configuración
        return restTemplate.getForObject(apiUrl, Configuracion.class);
    }
}
