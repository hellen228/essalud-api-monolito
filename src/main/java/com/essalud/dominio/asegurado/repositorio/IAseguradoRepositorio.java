package com.essalud.dominio.asegurado.repositorio;

import com.essalud.dominio.asegurado.modelo.Asegurado;

import java.util.List;
import java.util.Optional;

public interface IAseguradoRepositorio {
    Asegurado save(Asegurado asegurado);
    Optional<Asegurado> findById(Long id);
    Optional<Asegurado> findByDni(String dni);
    List<Asegurado> findAll();
    void deleteById(Long id);
}
