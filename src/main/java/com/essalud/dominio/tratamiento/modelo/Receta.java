package com.essalud.dominio.tratamiento.modelo;

public class Receta {
    private String id;
    private String diagnosticoId;
    private String medicamento;
    private String estado; // "Pendiente"

    public Receta(String id, String diagnosticoId, String medicamento, String estado) {
        this.id = id;
        this.diagnosticoId = diagnosticoId;
        this.medicamento = medicamento;
        this.estado = estado;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDiagnosticoId() { return diagnosticoId; }
    public void setDiagnosticoId(String diagnosticoId) { this.diagnosticoId = diagnosticoId; }

    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}