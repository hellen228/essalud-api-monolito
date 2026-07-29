package com.essalud.dominio.cita.repositorio;

import com.essalud.dominio.cita.modelo.Cita;

import java.util.List;
import java.util.Optional;

public interface ICitaRepositorio {

    Cita guardar(Cita cita);

    Optional<Cita> buscarPorId(Integer idCita);

    List<Cita> buscarPorDniPaciente(String dniPaciente);

    List<Cita> listarTodas();
}