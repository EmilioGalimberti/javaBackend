package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agencia/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/promo")
    public void sendPromotionNotification(@RequestBody List<String> emails) {
        for (String email : emails) {
            emailService.enviarPromocion(email);
        }
    }
}
