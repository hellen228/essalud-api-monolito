// dominio/acreditacion/modelo/Cobertura.java
package com.essalud.dominio.acreditacion.modelo;

import java.time.LocalDate;

public class Cobertura {

    private Integer idCobertura;
    private String dni;
    private boolean estado; // true = activo, false = inactivo
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Cobertura(Integer idCobertura, String dni, boolean estado,
                     LocalDate fechaInicio, LocalDate fechaFin) {
        this.idCobertura = idCobertura;
        this.dni = dni;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdCobertura() { return idCobertura; }
    public String getDni() { return dni; }
    public boolean isEstado() { return estado; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
}