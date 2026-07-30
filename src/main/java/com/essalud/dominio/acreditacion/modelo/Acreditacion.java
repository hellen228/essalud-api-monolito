package com.essalud.dominio.acreditacion.modelo;

public class Acreditacion {
    private Integer idAsegurado;
    private EstadoAcreditacion estado;
    private String motivoRechazo;

    public Acreditacion(Integer idAsegurado, EstadoAcreditacion estado, String motivoRechazo) {
        this.idAsegurado = idAsegurado;
        this.estado = estado;
        this.motivoRechazo = motivoRechazo;
    }

    public Integer getIdAsegurado() { return idAsegurado; }
    public EstadoAcreditacion getEstado() { return estado; }
    public void setEstado(EstadoAcreditacion estado) { this.estado = estado; }
    public String getMotivoRechazo() { return motivoRechazo; }
    public void setMotivoRechazo(String motivoRechazo) { this.motivoRechazo = motivoRechazo; }
}