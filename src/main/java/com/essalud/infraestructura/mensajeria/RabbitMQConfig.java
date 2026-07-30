package com.essalud.infraestructura.mensajeria;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String COLA_SOLICITUD_CITAS =
            "citas.solicitud";

    @Bean
    public Queue colaSolicitudCitas() {
        return new Queue(COLA_SOLICITUD_CITAS, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}