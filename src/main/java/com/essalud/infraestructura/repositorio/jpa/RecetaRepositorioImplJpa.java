package com.essalud.infraestructura.repositorio.jpa;

import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Receta> buscarPorId(Integer id) {
        return recetaJpaRepository.findById(id);
    }

    @Override
    public List<Receta> buscarPorPlanTerapeutico(Integer planTerapeuticoId) {
        return recetaJpaRepository.findByPlanTerapeuticoId(planTerapeuticoId);
    }

    @Override
    public Receta actualizar(Receta receta) {
        return recetaJpaRepository.save(receta);
    }

    @Override
    public void eliminar(Integer id) {
        recetaJpaRepository.deleteById(id);
    }

    @Override
    public Receta actualizarEstado(Integer id, String estado) {
        Receta receta = recetaJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prescripción no encontrada"));
        receta.setEstadoDispensacion(estado);
        return recetaJpaRepository.save(receta);
    }
}