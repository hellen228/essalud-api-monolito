package com.essalud.dominio.cita.repositorio;

import com.essalud.dominio.cita.modelo.Cita;
import java.util.Optional;

public interface ICitaRepositorio {
    Optional<Cita> buscarPorId(Integer id);
}