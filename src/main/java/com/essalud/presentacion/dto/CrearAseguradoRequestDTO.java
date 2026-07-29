package com.essalud.presentacion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Datos para crear un asegurado")
public class CrearAseguradoRequestDTO {

    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 8, message = "DNI debe tener 8 dígitos")
    @Schema(description = "DNI del asegurado", example = "12345678")
    private String dni;

    @NotBlank(message = "Nombres son obligatorios")
    @Schema(description = "Nombres del asegurado", example = "Juan Carlos")
    private String nombres;

    @NotBlank(message = "Apellidos son obligatorios")
    @Schema(description = "Apellidos del asegurado", example = "Perez Garcia")
    private String apellidos;

    @NotNull(message = "Fecha de nacimiento es obligatoria")
    @Schema(description = "Fecha de nacimiento", example = "1990-05-15")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "Sexo es obligatorio")
    @Size(min = 1, max = 1, message = "Sexo debe ser M o F")
    @Schema(description = "Sexo (M/F)", example = "M")
    private String sexo;

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
}
