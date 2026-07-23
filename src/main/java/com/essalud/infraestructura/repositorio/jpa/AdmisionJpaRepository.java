package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.admision.modelo.Admision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdmisionJpaRepository extends JpaRepository<Admision, Integer> {
    List<Admision> findByAseguradoId(Integer aseguradoId);
}