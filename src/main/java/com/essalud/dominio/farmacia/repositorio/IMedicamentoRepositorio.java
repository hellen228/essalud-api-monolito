package com.essalud.dominio.farmacia.repositorio;

import com.essalud.dominio.farmacia.modelo.Medicamento;
import java.util.Optional;

public interface IMedicamentoRepositorio {
    Optional<Medicamento> buscarPorId(Integer idMedicamento);
}