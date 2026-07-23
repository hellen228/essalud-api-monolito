package com.essalud.dominio.tratamiento.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "prescripcion_receta") 
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") 
    private Integer id; 

    @Column(name = "plan_terapeutico_id", nullable = false) 
    private Integer planTerapeuticoId; 

    @Column(name = "medicamento_id", nullable = false) 
    private Integer medicamentoId; 

    @Column(name = "dosis", nullable = false) 
    private String dosis;

    @Column(name = "frecuencia", nullable = false)
    private String frecuencia;

    @Column(name = "duracion", nullable = false)
    private String duracion;

    @Column(name = "estado_dispensacion")
    private String estadoDispensacion;

    public Receta() {
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public String getEstadoDispensacion() { return estadoDispensacion; }
    public void setEstadoDispensacion(String estadoDispensacion) { this.estadoDispensacion = estadoDispensacion; }
}