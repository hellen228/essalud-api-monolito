package com.essalud.dominio.atencionmedica.modelo;

import java.time.LocalDateTime;

public class AtencionMedica {

    private Long id;
    private Long idAsegurado;
    private String estado;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;

    public AtencionMedica() {}

    public AtencionMedica(Long id, Long idAsegurado, String estado) {
        this.id = id;
        this.idAsegurado = idAsegurado;
        this.estado = estado;
        this.fechaApertura = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdAsegurado() { return idAsegurado; }
    public void setIdAsegurado(Long idAsegurado) { this.idAsegurado = idAsegurado; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDateTime fechaApertura) { this.fechaApertura = fechaApertura; }

    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDateTime fechaCierre) { this.fechaCierre = fechaCierre; }
}
