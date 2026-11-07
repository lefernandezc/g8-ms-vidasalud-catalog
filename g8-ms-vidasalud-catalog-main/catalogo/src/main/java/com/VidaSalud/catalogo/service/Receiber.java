package com.VidaSalud.catalogo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiber {
    @RabbitListener(queues = "hola")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido: '" + message + "'");
    }
}
