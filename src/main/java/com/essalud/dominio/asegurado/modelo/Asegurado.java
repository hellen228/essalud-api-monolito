package com.essalud.dominio.asegurado.modelo;

public class Asegurado {
    private Integer id;
    private String dni;
    private boolean seguroActivo;

    public Asegurado() {}

    public Asegurado(Integer id, String dni, boolean seguroActivo) {
        this.id = id;
        this.dni = dni;
        this.seguroActivo = seguroActivo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public boolean isSeguroActivo() { return seguroActivo; }
    public void setSeguroActivo(boolean seguroActivo) { this.seguroActivo = seguroActivo; }
}