package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.tratamiento.modelo.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaJpaRepository extends JpaRepository<Receta, Integer> {
}