package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.tratamiento.modelo.PlanTerapeutico;
import com.essalud.dominio.tratamiento.repositorio.IPlanTerapeuticoRepositorio;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class PlanTerapeuticoRepositorioImplJpa implements IPlanTerapeuticoRepositorio {

    private final PlanTerapeuticoJpaRepository planTerapeuticoJpaRepository;

    public PlanTerapeuticoRepositorioImplJpa(PlanTerapeuticoJpaRepository planTerapeuticoJpaRepository) {
        this.planTerapeuticoJpaRepository = planTerapeuticoJpaRepository;
    }

    @Override
    public PlanTerapeutico guardar(PlanTerapeutico planTerapeutico) {
        return planTerapeuticoJpaRepository.save(planTerapeutico);
    }

    @Override
    public Optional<PlanTerapeutico> buscarPorId(Integer id) {
        return planTerapeuticoJpaRepository.findById(id);
    }

    @Override
    public List<PlanTerapeutico> buscarPorDiagnostico(Integer diagnosticoId) {
        return planTerapeuticoJpaRepository.findByDiagnosticoId(diagnosticoId);
    }

    @Override
    public PlanTerapeutico actualizar(PlanTerapeutico planTerapeutico) {
        return planTerapeuticoJpaRepository.save(planTerapeutico);
    }

    @Override
    public void eliminar(Integer id) {
        planTerapeuticoJpaRepository.deleteById(id);
    }
}
