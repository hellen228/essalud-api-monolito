package com.essalud.dominio.admision.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admision_triaje")
public class Admision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "asegurado_id", nullable = false)
    private Integer aseguradoId;

    @Column(name = "cita_id")
    private Integer citaId; // Puede ser null si es una emergencia

    @Column(name = "sala_id")
    private Integer salaId;

    @Column(name = "cama_id")
    private Integer camaId;

    @Column(name = "tipo_ingreso", nullable = false, length = 30)
    private String tipoIngreso; // Ej: "Emergencia", "Cita Programada"

    @Column(name = "prioridad_triaje", length = 5)
    private String prioridadTriaje; // Ej: "I", "II", "III", "IV", "V"

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;

    @PrePersist
    protected void onCreate() {
        if (this.fechaIngreso == null) {
            this.fechaIngreso = LocalDateTime.now();
        }
    }

    // Constructores, Getters y Setters
    public Admision() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }
}