package com.essalud.dominio.tratamiento.repositorio;

import com.essalud.dominio.tratamiento.modelo.Receta;

import java.util.List;
import java.util.Optional;

public interface IRecetaRepositorio {
    Receta guardar(Receta receta);
    Optional<Receta> buscarPorId(Integer id);
    List<Receta> buscarPorPlanTerapeutico(Integer planTerapeuticoId);
    Receta actualizar(Receta receta);
    void eliminar(Integer id);
    Receta actualizarEstado(Integer id, String estado);
}