package com.essalud.dominio.cita.modelo;

import java.time.LocalDateTime;

public class Cita {
    private Integer id;
    private Integer aseguradoId;
    private Integer medicoId;
    private LocalDateTime fechaHora;
    private String estado;

    public Cita() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getAseguradoId() { return aseguradoId; }
    public void setAseguradoId(Integer aseguradoId) { this.aseguradoId = aseguradoId; }
    public Integer getMedicoId() { return medicoId; }
    public void setMedicoId(Integer medicoId) { this.medicoId = medicoId; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}