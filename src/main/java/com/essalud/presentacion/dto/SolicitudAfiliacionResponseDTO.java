package com.essalud.presentacion.dto;

import com.essalud.dominio.asegurado.modelo.EstadoSolicitud;
import com.essalud.dominio.asegurado.modelo.SolicitudAfiliacion;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Respuesta de solicitud de afiliación")
public class SolicitudAfiliacionResponseDTO {

    @Schema(description = "ID de la solicitud")
    private Long id;

    @Schema(description = "DNI del solicitante", example = "12345678")
    private String dni;

    @Schema(description = "Fecha de solicitud")
    private LocalDateTime fechaSolicitud;

    @Schema(description = "Estado de la solicitud")
    private EstadoSolicitud estado;

    public static SolicitudAfiliacionResponseDTO fromDomain(SolicitudAfiliacion solicitud) {
        SolicitudAfiliacionResponseDTO dto = new SolicitudAfiliacionResponseDTO();
        dto.setId(solicitud.getId());
        dto.setDni(solicitud.getDni());
        dto.setFechaSolicitud(solicitud.getFechaSolicitud());
        dto.setEstado(solicitud.getEstado());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public EstadoSolicitud getEstado() { return estado; }
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }
}
