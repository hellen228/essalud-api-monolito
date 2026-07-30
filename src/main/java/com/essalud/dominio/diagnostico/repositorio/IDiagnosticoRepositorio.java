package com.essalud.dominio.diagnostico.repositorio;

import com.essalud.dominio.diagnostico.modelo.Diagnostico;
import java.util.List;
import java.util.Optional;

public interface IDiagnosticoRepositorio {

    Diagnostico guardar(Diagnostico diagnostico);

    Optional<Diagnostico> buscarPorId(Long id);

    List<Diagnostico> listarTodos();
}
