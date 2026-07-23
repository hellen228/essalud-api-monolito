package com.essalud.presentacion.dto;

public class EmitirRecetaDTO {
    private Integer planTerapeuticoId; 
    private Integer medicamentoId;     
    private String dosis;
    private String frecuencia;
    private String duracion;

    // Getters y Setters
    public Integer getPlanTerapeuticoId() { return planTerapeuticoId; }
    public void setPlanTerapeuticoId(Integer planTerapeuticoId) { this.planTerapeuticoId = planTerapeuticoId; }

    public Integer getMedicamentoId() { return medicamentoId; }
    public void setMedicamentoId(Integer medicamentoId) { this.medicamentoId = medicamentoId; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
}