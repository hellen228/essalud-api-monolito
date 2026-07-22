package com.essalud.presentacion.dto;

import com.essalud.dominio.asegurado.modelo.EstadoAfiliacion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Actualización de estado de afiliación")
public class ActualizarEstadoRequestDTO {

    @NotNull(message = "Estado es obligatorio")
    @Schema(description = "Nuevo estado de afiliación", example = "ACTIVO")
    private EstadoAfiliacion estado;

    public EstadoAfiliacion getEstado() { return estado; }
    public void setEstado(EstadoAfiliacion estado) { this.estado = estado; }
}
