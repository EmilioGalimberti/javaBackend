package com.example.demo.config;

import org.hibernate.service.spi.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() throws ServiceException {
        return new RestTemplate();
    }
}
