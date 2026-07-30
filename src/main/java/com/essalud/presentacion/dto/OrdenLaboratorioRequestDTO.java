package com.essalud.presentacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para generar una orden de examen")
public class OrdenLaboratorioRequestDTO {

    @Schema(description = "ID de la atencion medica", example = "1")
    private Long idAtencion;

    @Schema(description = "Tipo de examen", example = "HEMATOLOGIA_COMPLETA")
    private String tipoExamen;

    @Schema(description = "Zona del cuerpo para Rayos X", example = "TORAX_AP")
    private String zonaCuerpo;

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public String getTipoExamen() { return tipoExamen; }
    public void setTipoExamen(String tipoExamen) { this.tipoExamen = tipoExamen; }

    public String getZonaCuerpo() { return zonaCuerpo; }
    public void setZonaCuerpo(String zonaCuerpo) { this.zonaCuerpo = zonaCuerpo; }
}
