package com.essalud.presentacion.dto;

import java.time.LocalDate;

public class HorarioResponseDTO {

    private Integer idHorario;
    private String nombreMedico;
    private LocalDate fechaDisponible;
    private String horaDisponible;

    public HorarioResponseDTO() {
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
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

}