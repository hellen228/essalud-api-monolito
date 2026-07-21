package com.essalud.dominio.farmacia.modelo;

public class Medicamento {
    private Integer id;
    private String nombreFarmaco;
    private Integer stockActual;
    private Boolean esControlado;

    public Medicamento(Integer id, String nombreFarmaco, Integer stockActual, Boolean esControlado) {
        this.id = id;
        this.nombreFarmaco = nombreFarmaco;
        this.stockActual = stockActual;
        this.esControlado = esControlado;
    }

    // Getters
    public Integer getId() { return id; }
    public String getNombreFarmaco() { return nombreFarmaco; }
    public Integer getStockActual() { return stockActual; }
    public Boolean getEsControlado() { return esControlado; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setNombreFarmaco(String nombreFarmaco) { this.nombreFarmaco = nombreFarmaco; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
    public void setEsControlado(Boolean esControlado) { this.esControlado = esControlado; }
}