package com.essalud.dominio.asegurado.modelo;

import java.time.LocalDate;

public class Asegurado {
    private String dni;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String sexo;
    private EstadoAfiliacion estadoAfiliacion;
    private LocalDate fechaAfiliacion;

    public Asegurado() {
    }

    public Asegurado(String dni, String nombres, String apellidos,
                     LocalDate fechaNacimiento, String sexo) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.estadoAfiliacion = EstadoAfiliacion.PENDIENTE;
        this.fechaAfiliacion = LocalDate.now();
    }

    // Convenience helper for older code expecting a boolean insurance check
    public boolean isSeguroActivo() {
        return this.estadoAfiliacion == EstadoAfiliacion.ACTIVO;
    }

    public Long getId() {
        if (this.dni == null) {
            return null;
        }
        return Long.parseLong(this.dni);
    }

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

    public LocalDate getFechaAfiliacion() { return fechaAfiliacion; }
    public void setFechaAfiliacion(LocalDate fechaAfiliacion) { this.fechaAfiliacion = fechaAfiliacion; }
}
