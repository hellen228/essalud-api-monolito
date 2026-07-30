package com.essalud.infraestructura.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "essalud.diagnostico";
    public static final String QUEUE = "diagnostico.eventos";
    public static final String ROUTING_KEY = "diagnostico.#";

    @Bean
    public TopicExchange diagnosticoExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue diagnosticoQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding diagnosticoBinding() {
        return BindingBuilder.bind(diagnosticoQueue())
                .to(diagnosticoExchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory,
                                          Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}
