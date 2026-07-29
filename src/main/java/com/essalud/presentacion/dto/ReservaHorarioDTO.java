package com.essalud.presentacion.dto;

public class ReservaHorarioDTO {

    private String dniPaciente;
    private Integer idHorario;

    public ReservaHorarioDTO() {
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }
}