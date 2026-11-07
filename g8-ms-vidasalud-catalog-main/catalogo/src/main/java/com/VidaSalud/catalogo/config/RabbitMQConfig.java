package com.VidaSalud.catalogo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${messaging.queue.catalogo}")
    private String queueName;

    @Value("${messaging.exchange.catalogo}")
    private String exchangeName;

    @Value("${messaging.routing-key.catalogo}")
    private String routingKey;

    @Bean
    public Queue catalogoQueue() {
        return QueueBuilder.durable(queueName)
                .withArgument("x-dead-letter-exchange", "dlq.exchange")
                .withArgument("x-dead-letter-routing-key", queueName + ".routing.dlq")
                .build();
    }

    @Bean
    public DirectExchange catalogoExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding catalogoBinding(Queue catalogoQueue, DirectExchange catalogoExchange) {
        return BindingBuilder.bind(catalogoQueue).to(catalogoExchange).with(routingKey);
    }
}