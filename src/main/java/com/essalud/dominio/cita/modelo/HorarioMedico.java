package com.essalud.dominio.cita.modelo;

import java.time.LocalDate;

public class HorarioMedico {

    private Integer idHorario;
    private Integer idMedico;
    private String nombreMedico;
    private String especialidad;
    private LocalDate fechaDisponible;
    private String horaDisponible;
    private Boolean disponible;

    public HorarioMedico() {
    }

    public HorarioMedico(
            Integer idHorario,
            Integer idMedico,
            String nombreMedico,
            String especialidad,
            LocalDate fechaDisponible,
            String horaDisponible,
            Boolean disponible) {

        this.idHorario = idHorario;
        this.idMedico = idMedico;
        this.nombreMedico = nombreMedico;
        this.especialidad = especialidad;
        this.fechaDisponible = fechaDisponible;
        this.horaDisponible = horaDisponible;
        this.disponible = disponible;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFechaDisponible() {
        return fechaDisponible;
    }

    public void setFechaDisponible(LocalDate fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
    }

    public String getHoraDisponible() {
        return horaDisponible;
    }

    public void setHoraDisponible(String horaDisponible) {
        this.horaDisponible = horaDisponible;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public boolean isDisponible() {
        return Boolean.TRUE.equals(disponible);
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}