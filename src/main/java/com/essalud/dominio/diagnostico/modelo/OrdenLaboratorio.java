package com.essalud.dominio.diagnostico.modelo;

import java.time.LocalDateTime;

public class OrdenLaboratorio {

    private Long id;
    private Long idAtencion;
    private String tipoExamen;
    private String zonaCuerpo;
    private String estado;
    private String resultado;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaResultado;

    public OrdenLaboratorio() {}

    public OrdenLaboratorio(Long idAtencion, String tipoExamen, String zonaCuerpo) {
        this.idAtencion = idAtencion;
        this.tipoExamen = tipoExamen;
        this.zonaCuerpo = zonaCuerpo;
        this.estado = "SOLICITADO";
        this.fechaSolicitud = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Long idAtencion) { this.idAtencion = idAtencion; }

    public String getTipoExamen() { return tipoExamen; }
    public void setTipoExamen(String tipoExamen) { this.tipoExamen = tipoExamen; }

    public String getZonaCuerpo() { return zonaCuerpo; }
    public void setZonaCuerpo(String zonaCuerpo) { this.zonaCuerpo = zonaCuerpo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) {
        this.resultado = resultado;
        this.estado = "COMPLETADO";
        this.fechaResultado = LocalDateTime.now();
    }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDateTime getFechaResultado() { return fechaResultado; }
    public void setFechaResultado(LocalDateTime fechaResultado) { this.fechaResultado = fechaResultado; }
}
