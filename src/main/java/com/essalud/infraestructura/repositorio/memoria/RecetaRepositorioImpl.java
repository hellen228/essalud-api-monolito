package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Remueve o comenta @Repository para no usar memoria
public class RecetaRepositorioImpl implements IRecetaRepositorio {

    private final Map<Integer, Receta> dbMemoria = new HashMap<>();

    @Override
    public Receta guardar(Receta receta) {
        if (receta.getId() == null) {
            receta.setId((int) (System.currentTimeMillis() % 100000));
        }
        dbMemoria.put(receta.getId(), receta);
        return receta;
    }

    @Override
    public Optional<Receta> buscarPorId(Integer id) {
        return Optional.ofNullable(dbMemoria.get(id));
    }

    @Override
    public List<Receta> buscarPorPlanTerapeutico(Integer planTerapeuticoId) {
        return dbMemoria.values().stream()
                .filter(receta -> planTerapeuticoId.equals(receta.getPlanTerapeuticoId()))
                .toList();
    }

    @Override
    public Receta actualizar(Receta receta) {
        if (receta.getId() == null) {
            receta.setId((int) (System.currentTimeMillis() % 100000));
        }
        dbMemoria.put(receta.getId(), receta);
        return receta;
    }

    @Override
    public void eliminar(Integer id) {
        dbMemoria.remove(id);
    }

    @Override
    public Receta actualizarEstado(Integer id, String estado) {
        Receta receta = buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Prescripción no encontrada"));
        receta.setEstadoDispensacion(estado);
        return actualizar(receta);
    }
}