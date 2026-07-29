package com.essalud.dominio.asegurado.modelo;

import java.time.LocalDateTime;

public class SolicitudAfiliacion {
    private Long id;
    private String dni;
    private LocalDateTime fechaSolicitud;
    private EstadoSolicitud estado;

    public SolicitudAfiliacion() {
    }

    public SolicitudAfiliacion(String dni) {
        this.dni = dni;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public EstadoSolicitud getEstado() { return estado; }
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }
}
