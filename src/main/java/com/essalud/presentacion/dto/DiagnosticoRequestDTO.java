package com.essalud.presentacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud para asignar un diagnostico CIE-10")
public class DiagnosticoRequestDTO {

    @Schema(description = "ID de la atencion medica", example = "1")
    private Long idAtencion;

    @Schema(description = "Codigo CIE-10 del diagnostico", example = "J18.9")
    private String codigoCie10;

    @Schema(description = "Descripcion del diagnostico", example = "Neumonia no especificada")
    private String descripcion;

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public String getCodigoCie10() { return codigoCie10; }
    public void setCodigoCie10(String codigoCie10) { this.codigoCie10 = codigoCie10; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
