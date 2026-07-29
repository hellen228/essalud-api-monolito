package com.essalud.dominio.cita.repositorio;

import com.essalud.dominio.cita.modelo.HorarioMedico;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHorarioRepositorio {

    List<HorarioMedico> buscarDisponiblesPorEspecialidadYFecha(
            String especialidad,
            LocalDate fecha
    );

    Optional<HorarioMedico> buscarPorId(Integer idHorario);

    HorarioMedico guardar(HorarioMedico horario);

    List<HorarioMedico> listarTodos();
}