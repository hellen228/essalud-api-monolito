package com.essalud.infraestructura.mensajeria;

import com.essalud.aplicacion.servicios.ICitaServicio;
import com.essalud.dominio.cita.modelo.Cita;
import com.essalud.presentacion.dto.ReservaHorarioDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CitaRabbitListener {

    private final ICitaServicio citaServicio;

    public CitaRabbitListener(ICitaServicio citaServicio) {
        this.citaServicio = citaServicio;
    }

    @RabbitListener(queues = RabbitMQConfig.COLA_SOLICITUD_CITAS)
    public void recibirSolicitudCita(ReservaHorarioDTO request) {

        try {
            System.out.println("=================================");
            System.out.println("Solicitud recibida desde RabbitMQ");
            System.out.println("DNI: " + request.getDniPaciente());
            System.out.println("ID horario: " + request.getIdHorario());

            Cita cita = citaServicio.reservarHorario(request);

            System.out.println("Cita registrada correctamente");
            System.out.println("ID cita: " + cita.getIdCita());
            System.out.println("Estado: " + cita.getEstadoCita());
            System.out.println("=================================");

        } catch (RuntimeException e) {
            System.err.println("Error al registrar cita desde RabbitMQ:");
            System.err.println(e.getMessage());
        }
    }
}