package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary 
public class RecetaRepositorioImplJpa implements IRecetaRepositorio {

    private final RecetaJpaRepository recetaJpaRepository;

    public RecetaRepositorioImplJpa(RecetaJpaRepository recetaJpaRepository) {
        this.recetaJpaRepository = recetaJpaRepository;
    }

    @Override
    public Receta guardar(Receta receta) {
        return recetaJpaRepository.save(receta);
    }
}