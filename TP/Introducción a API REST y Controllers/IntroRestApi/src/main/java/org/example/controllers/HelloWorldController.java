package org.example.controllers;

import org.example.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    //@Autowired es una anotación de Spring que se usa para inyectar automáticamente dependencias.
    // Es decir, cuando vos necesitás una clase (como un servicio) dentro de otra clase (como un controlador),
    // Spring la crea por vos y te la pasa.
    //Spring ve que tu clase HelloWorldController necesita una instancia de HelloWorldService, entonces:
    //
    //Busca una clase marcada como @Service, @Component o @Bean que sea del tipo HelloWorldService.
    //
    //Crea una instancia de esa clase (o reutiliza una si ya está creada).
    //
    //Se la pasa al constructor del controlador.
    @Autowired
    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("hello")
    public ResponseEntity<String> helloWorld() {
        String message = helloWorldService.getHelloMessage();
        return ResponseEntity.ok(message);
    }
}
