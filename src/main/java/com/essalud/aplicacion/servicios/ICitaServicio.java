package com.essalud.aplicacion.servicios;

import com.essalud.dominio.cita.modelo.Cita;
import com.essalud.dominio.cita.modelo.HorarioMedico;
import com.essalud.presentacion.dto.ReservaHorarioDTO;

import java.time.LocalDate;
import java.util.List;

public interface ICitaServicio {

    List<HorarioMedico> consultarHorariosDisponibles(
            String especialidad,
            LocalDate fecha
    );

    Cita reservarHorario(ReservaHorarioDTO request);

    Cita consultarCita(Integer idCita);

    List<Cita> consultarCitasPorPaciente(String dniPaciente);
}