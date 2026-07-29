package com.essalud.infraestructura.messaging.listener;

import com.essalud.aplicacion.servicios.TratamientoServicioImpl;
import com.essalud.presentacion.dto.EmitirRecetaDTO;
import com.essalud.infraestructura.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BonitaEventListener {

    private final TratamientoServicioImpl tratamientoServicio;

    public BonitaEventListener(TratamientoServicioImpl tratamientoServicio) {
        this.tratamientoServicio = tratamientoServicio;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RECETAS)
    public void recibirMensajeDesdeBonita(EmitirRecetaDTO dto) {
        // Ejecuta el servicio de aplicación para validar y guardar en la BD
        tratamientoServicio.emitirReceta(dto);
    }
}