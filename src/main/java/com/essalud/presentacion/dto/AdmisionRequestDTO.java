package com.essalud.presentacion.dto;

public class AdmisionRequestDTO {
    private Integer aseguradoId;
    private Integer citaId;
    private Integer salaId;
    private Integer camaId;
    private String tipoIngreso;
    private String prioridadTriaje;

    // Getters y Setters
    public Integer getAseguradoId() { return aseguradoId; }
    public void setAseguradoId(Integer aseguradoId) { this.aseguradoId = aseguradoId; }

    public Integer getCitaId() { return citaId; }
    public void setCitaId(Integer citaId) { this.citaId = citaId; }

    public Integer getSalaId() { return salaId; }
    public void setSalaId(Integer salaId) { this.salaId = salaId; }

    public Integer getCamaId() { return camaId; }
    public void setCamaId(Integer camaId) { this.camaId = camaId; }

    public String getTipoIngreso() { return tipoIngreso; }
    public void setTipoIngreso(String tipoIngreso) { this.tipoIngreso = tipoIngreso; }

    public String getPrioridadTriaje() { return prioridadTriaje; }
    public void setPrioridadTriaje(String prioridadTriaje) { this.prioridadTriaje = prioridadTriaje; }
}