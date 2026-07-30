package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.tratamiento.modelo.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaJpaRepository extends JpaRepository<Receta, Integer> {
    List<Receta> findByPlanTerapeuticoId(Integer planTerapeuticoId);
}