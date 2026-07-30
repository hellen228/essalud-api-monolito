package com.essalud.infraestructura.adaptadores;

import com.essalud.infraestructura.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class DiagnosticoEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(DiagnosticoEventPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    public DiagnosticoEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarEvento(String accion, Map<String, Object> datos) {
        Map<String, Object> mensaje = Map.of(
                "accion", accion,
                "datos", datos,
                "timestamp", System.currentTimeMillis()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "diagnostico." + accion.toLowerCase(), mensaje);
        log.info("Evento publicado: {} -> diagnostico.{}", accion, accion.toLowerCase());
    }
}
