package com.essalud.infraestructura.messaging.listener;

import com.essalud.aplicacion.servicios.TratamientoServicioImpl;
import com.essalud.infraestructura.config.RabbitMQConfig;
import com.essalud.presentacion.dto.AgregarPrescripcionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BonitaEventListener {

    private final TratamientoServicioImpl tratamientoServicio;

    public BonitaEventListener(TratamientoServicioImpl tratamientoServicio) {
        this.tratamientoServicio = tratamientoServicio;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RECETAS)
    public void recibirMensajeRabbit(Map<String, Object> payload) {
        System.out.println("Mensaje recibido de Bonita: " + payload);

        Object planIdRaw = payload.get("idDiagnostico"); 
            return;
        }
        Integer planId = Integer.parseInt(planIdRaw.toString());

        List<Map<String, Object>> listaDosis = (List<Map<String, Object>>) payload.get("dosis");

        if (listaDosis != null) {
            for (Map<String, Object> item : listaDosis) {
                AgregarPrescripcionDTO dto = new AgregarPrescripcionDTO();
                
                if (item.get("idMedicamento") != null) {
                    dto.setMedicamentoId(Integer.parseInt(item.get("idMedicamento").toString()));
                }
                
                dto.setDosis(item.get("cantidad") != null ? item.get("cantidad").toString() : "");
                dto.setFrecuencia(item.get("indicaciones") != null ? item.get("indicaciones").toString() : "");
                dto.setDuracion(" "); 

                tratamientoServicio.agregarPrescripcion(planId, dto);
            }
        }
    }
}