package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.tratamiento.modelo.PlanTerapeutico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanTerapeuticoJpaRepository extends JpaRepository<PlanTerapeutico, Integer> {
    List<PlanTerapeutico> findByDiagnosticoId(Integer diagnosticoId);
}
