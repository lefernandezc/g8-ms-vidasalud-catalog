package com.VidaSalud.catalogo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatalogoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${messaging.exchange.catalogo}")
    private String exchange;

    @Value("${messaging.routing-key.catalogo}")
    private String routingKey;

    public CatalogoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notificarCambioCatalogo(String mensaje) {
        rabbitTemplate.convertAndSend(exchange, routingKey, mensaje);
        System.out.println("Notificación de catálogo enviada a RabbitMQ.");
    }
}