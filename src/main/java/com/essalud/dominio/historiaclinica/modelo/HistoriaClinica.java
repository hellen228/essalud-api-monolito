package com.essalud.dominio.historiaclinica.modelo;

import java.time.LocalDate;

public class HistoriaClinica {
    private Integer id;
    private Integer aseguradoId;
    private LocalDate fechaApertura;
    private String grupoSanguineo;

    public HistoriaClinica() {
        this.fechaApertura = LocalDate.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getAseguradoId() { return aseguradoId; }
    public void setAseguradoId(Integer aseguradoId) { this.aseguradoId = aseguradoId; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate fechaApertura) { this.fechaApertura = fechaApertura; }
    public String getGrupoSanguineo() { return grupoSanguineo; }
    public void setGrupoSanguineo(String grupoSanguineo) { this.grupoSanguineo = grupoSanguineo; }
}