package com.essalud.presentacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Solicitud de validación de identidad")
public class ValidarIdentidadRequestDTO {

    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 8, message = "DNI debe tener 8 dígitos")
    @Schema(description = "DNI a validar", example = "12345678")
    private String dni;

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}
