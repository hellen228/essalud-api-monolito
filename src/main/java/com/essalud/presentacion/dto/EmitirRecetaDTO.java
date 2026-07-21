package com.essalud.presentacion.dto;

public class EmitirRecetaDTO {
    private String diagnosticoId;
    private String medicamento;

    public String getDiagnosticoId() { return diagnosticoId; }
    public void setDiagnosticoId(String diagnosticoId) { this.diagnosticoId = diagnosticoId; }

    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }
}