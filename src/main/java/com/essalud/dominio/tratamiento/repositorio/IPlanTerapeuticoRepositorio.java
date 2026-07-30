package com.essalud.dominio.tratamiento.repositorio;

import com.essalud.dominio.tratamiento.modelo.PlanTerapeutico;

import java.util.List;
import java.util.Optional;

public interface IPlanTerapeuticoRepositorio {
    PlanTerapeutico guardar(PlanTerapeutico planTerapeutico);
    Optional<PlanTerapeutico> buscarPorId(Integer id);
    List<PlanTerapeutico> buscarPorDiagnostico(Integer diagnosticoId);
    PlanTerapeutico actualizar(PlanTerapeutico planTerapeutico);
    void eliminar(Integer id);
}
