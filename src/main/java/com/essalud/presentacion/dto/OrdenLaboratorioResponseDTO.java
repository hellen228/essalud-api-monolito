package com.essalud.presentacion.dto;

import com.essalud.dominio.diagnostico.modelo.OrdenLaboratorio;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Respuesta con datos de la orden de examen")
public class OrdenLaboratorioResponseDTO {

    @Schema(description = "ID de la orden", example = "1")
    private Long id;

    @Schema(description = "ID de la atencion medica", example = "1")
    private Long idAtencion;

    @Schema(description = "Tipo de examen", example = "HEMATOLOGIA_COMPLETA")
    private String tipoExamen;

    @Schema(description = "Zona del cuerpo", example = "TORAX_AP")
    private String zonaCuerpo;

    @Schema(description = "Estado de la orden", example = "SOLICITADO")
    private String estado;

    @Schema(description = "Resultado del examen")
    private String resultado;

    @Schema(description = "Fecha de solicitud")
    private LocalDateTime fechaSolicitud;

    @Schema(description = "Fecha de resultado")
    private LocalDateTime fechaResultado;

    public static OrdenLaboratorioResponseDTO fromEntity(OrdenLaboratorio o) {
        OrdenLaboratorioResponseDTO dto = new OrdenLaboratorioResponseDTO();
        dto.setId(o.getId());
        dto.setIdAtencion(o.getIdAtencion());
        dto.setTipoExamen(o.getTipoExamen());
        dto.setZonaCuerpo(o.getZonaCuerpo());
        dto.setEstado(o.getEstado());
        dto.setResultado(o.getResultado());
        dto.setFechaSolicitud(o.getFechaSolicitud());
        dto.setFechaResultado(o.getFechaResultado());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public String getTipoExamen() { return tipoExamen; }
    public void setTipoExamen(String tipoExamen) { this.tipoExamen = tipoExamen; }

    public String getZonaCuerpo() { return zonaCuerpo; }
    public void setZonaCuerpo(String zonaCuerpo) { this.zonaCuerpo = zonaCuerpo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDateTime getFechaResultado() { return fechaResultado; }
    public void setFechaResultado(LocalDateTime fechaResultado) { this.fechaResultado = fechaResultado; }
}
