package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;

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