package com.essalud.presentacion.dto;

import com.essalud.dominio.diagnostico.modelo.Diagnostico;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Respuesta con datos del diagnostico")
public class DiagnosticoResponseDTO {

    @Schema(description = "ID del diagnostico", example = "1")
    private Long id;

    @Schema(description = "ID de la atencion medica", example = "1")
    private Long idAtencion;

    @Schema(description = "Codigo CIE-10", example = "J18.9")
    private String codigoCie10;

    @Schema(description = "Descripcion del diagnostico", example = "Neumonia no especificada")
    private String descripcion;

    @Schema(description = "Estado del diagnostico", example = "ACTIVO")
    private String estado;

    @Schema(description = "Fecha de asignacion")
    private LocalDateTime fechaAsignacion;

    public static DiagnosticoResponseDTO fromEntity(Diagnostico d) {
        DiagnosticoResponseDTO dto = new DiagnosticoResponseDTO();
        dto.setId(d.getId());
        dto.setIdAtencion(d.getIdAtencion());
        dto.setCodigoCie10(d.getCodigoCie10());
        dto.setDescripcion(d.getDescripcion());
        dto.setEstado(d.getEstado());
        dto.setFechaAsignacion(d.getFechaAsignacion());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public String getCodigoCie10() { return codigoCie10; }
    public void setCodigoCie10(String codigoCie10) { this.codigoCie10 = codigoCie10; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDateTime fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
}
