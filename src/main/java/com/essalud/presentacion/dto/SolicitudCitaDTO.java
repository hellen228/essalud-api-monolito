package com.essalud.presentacion.dto;

public class SolicitudCitaDTO {

    private String dniPaciente;
    private String especialidad;

    public SolicitudCitaDTO() {
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}