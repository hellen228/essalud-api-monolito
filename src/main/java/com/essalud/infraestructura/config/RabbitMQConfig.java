package com.essalud.infraestructura.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_RECETAS = "essalud.recetas.queue";
    public static final String EXCHANGE_BONITA = "essalud.exchange";
    public static final String ROUTING_KEY_RECETA = "receta.crear";

    @Bean
    public Queue recetaQueue() {
        return new Queue(QUEUE_RECETAS, true); // Queue durable
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_BONITA);
    }

    @Bean
    public Binding binding(Queue recetaQueue, TopicExchange exchange) {
        return BindingBuilder.bind(recetaQueue).to(exchange).with(ROUTING_KEY_RECETA);
    }

    // Para recibir/enviar DTOs como JSON automáticamente
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}