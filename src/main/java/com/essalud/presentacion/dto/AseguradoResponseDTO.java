package com.essalud.presentacion.dto;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.modelo.EstadoAfiliacion;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Datos del asegurado")
public class AseguradoResponseDTO {

    @Schema(description = "ID del asegurado", example = "1")
    private Long id;

    @Schema(description = "DNI", example = "12345678")
    private String dni;

    @Schema(description = "Nombres", example = "Juan Carlos")
    private String nombres;

    @Schema(description = "Apellidos", example = "Perez Garcia")
    private String apellidos;

    @Schema(description = "Fecha de nacimiento", example = "1990-05-15")
    private LocalDate fechaNacimiento;

    @Schema(description = "Sexo (M/F)", example = "M")
    private String sexo;

    @Schema(description = "Estado de afiliación", example = "ACTIVO")
    private EstadoAfiliacion estadoAfiliacion;

    @Schema(description = "Fecha de afiliación", example = "2026-07-22T10:30:00")
    private LocalDateTime fechaAfiliacion;

    public static AseguradoResponseDTO fromDomain(Asegurado asegurado) {
        AseguradoResponseDTO dto = new AseguradoResponseDTO();
        dto.setId(asegurado.getId());
        dto.setDni(asegurado.getDni());
        dto.setNombres(asegurado.getNombres());
        dto.setApellidos(asegurado.getApellidos());
        dto.setFechaNacimiento(asegurado.getFechaNacimiento());
        dto.setSexo(asegurado.getSexo());
        dto.setEstadoAfiliacion(asegurado.getEstadoAfiliacion());
        dto.setFechaAfiliacion(asegurado.getFechaAfiliacion());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public EstadoAfiliacion getEstadoAfiliacion() { return estadoAfiliacion; }
    public void setEstadoAfiliacion(EstadoAfiliacion estadoAfiliacion) { this.estadoAfiliacion = estadoAfiliacion; }

    public LocalDateTime getFechaAfiliacion() { return fechaAfiliacion; }
    public void setFechaAfiliacion(LocalDateTime fechaAfiliacion) { this.fechaAfiliacion = fechaAfiliacion; }
}
