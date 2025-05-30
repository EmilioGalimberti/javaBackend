package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private JavaMailSender mailSender;

    public void sendNotification(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("agenciadeventadevehiculos@gmail.com"); // Cambiar por tu correo
        mailSender.send(message);
    }

    public void enviarPromocion(String mail) {
        String email = "destinatario@gmail.com";
        String subject = "Nueva promocion tremenda!!";
        String text = "No te podes perder esta promocion! Es tremenda, veni y maneja un auto y llevate una rueda";
        sendNotification(email, subject, text);
    }
}
