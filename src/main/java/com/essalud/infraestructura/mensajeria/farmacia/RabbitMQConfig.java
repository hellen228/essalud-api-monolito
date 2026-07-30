package com.essalud.infraestructura.mensajeria.farmacia;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue colaSalidas() {
        return new Queue("cola-kardex-salidas", true);
    }

    @Bean
    public Queue colaInventario() {
        return new Queue("cola-inventario", true);
    }
}