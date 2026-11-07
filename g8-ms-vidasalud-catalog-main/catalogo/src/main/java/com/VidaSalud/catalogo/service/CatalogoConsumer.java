package com.VidaSalud.catalogo.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CatalogoConsumer {

    @RabbitListener(queues = "${messaging.queue.catalogo}")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            System.out.println("Procesando mensaje en Catálogo: " + message);
            // ACK manual si todo sale bien
            channel.basicAck(tag, false);
        } catch (Exception e) {
            System.err.println("Error procesando el mensaje: " + e.getMessage());
            // NACK manual si hay error se va a la DLQ
            channel.basicNack(tag, false, false);
        }
    }
}