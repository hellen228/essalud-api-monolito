package com.essalud.infraestructura.repositorio.jpa; // O en la carpeta de persistencia real

import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary // Hace que Spring use esta implementación en lugar de la versión en memoria
public class RecetaRepositorioImplJpa implements IRecetaRepositorio {

    private final RecetaSpringDataRepository jpaRepository;

    public RecetaRepositorioImplJpa(RecetaSpringDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Receta guardar(Receta receta) {
        // Guarda directamente en la tabla física de la Base de Datos
        return jpaRepository.save(receta);
    }
}