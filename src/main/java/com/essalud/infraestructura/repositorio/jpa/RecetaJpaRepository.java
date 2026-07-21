package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.tratamiento.modelo.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaJpaRepository extends JpaRepository<Receta, String> {
    // Spring Data genera automáticamente los métodos para la BD (save, findById, delete, etc.)
}