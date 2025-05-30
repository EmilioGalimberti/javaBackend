package com.example.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConf {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${uri.Pruebas}") String uriPruebas,
                                        @Value("${uri.Reportes}") String uriReportes
                                        ) {
        return builder.routes()
                // Ruteo al Microservicio de Pruebas
                .route(p -> p.path("/api/pruebas/**").uri(uriPruebas))

                // Ruteo al Microservicio de Pruebas
                .route(p -> p.path("/api/vehiculos/**").uri(uriPruebas))

                // Ruteo al Microservicio de Pruebas
                .route(p -> p.path("/api/empleados/**").uri(uriPruebas))

                // Ruteo al Microservicio de Reportes
                .route(p -> p.path("/api/reportes/**").uri(uriReportes))

                .build();
    }
}
