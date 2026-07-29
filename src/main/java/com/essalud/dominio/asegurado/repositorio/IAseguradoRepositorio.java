package com.essalud.dominio.asegurado.repositorio;

import com.essalud.dominio.asegurado.modelo.Asegurado;

import java.util.List;
import java.util.Optional;

public interface IAseguradoRepositorio {
    Asegurado save(Asegurado asegurado);
    Optional<Asegurado> findById(String id);
    Optional<Asegurado> findByDni(String dni);
    List<Asegurado> findAll();
    void deleteById(String id);

    default Optional<Asegurado> buscarPorId(String id) {
        return findById(id);
    }

    default Optional<Asegurado> buscarPorId(Integer id) {
        if (id == null) return Optional.empty();
        return findById(id.toString());
    }

    default Optional<Asegurado> buscarPorDni(String dni) {
        return findByDni(dni);
    }
}
