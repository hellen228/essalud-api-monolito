package com.essalud.dominio.diagnostico.modelo;

import java.time.LocalDateTime;

public class Diagnostico {

    private Long id;
    private Long idAtencion;
    private String codigoCie10;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaAsignacion;

    public Diagnostico() {}

    public Diagnostico(Long idAtencion, String codigoCie10, String descripcion) {
        this.idAtencion = idAtencion;
        this.codigoCie10 = codigoCie10;
        this.descripcion = descripcion;
        this.estado = "ACTIVO";
        this.fechaAsignacion = LocalDateTime.now();
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
