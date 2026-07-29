package com.essalud.dominio.asegurado.repositorio;

import com.essalud.dominio.asegurado.modelo.Asegurado;
<<<<<<< HEAD
import java.util.Optional;

public interface IAseguradoRepositorio {
    Optional<Asegurado> buscarPorId(Integer id);
    Optional<Asegurado> buscarPorDni(String dni);
}
=======

import java.util.List;
import java.util.Optional;

public interface IAseguradoRepositorio {
    Asegurado save(Asegurado asegurado);
    Optional<Asegurado> findById(Long id);
    Optional<Asegurado> findByDni(String dni);
    List<Asegurado> findAll();
    void deleteById(Long id);
}
>>>>>>> feature/asegurado-service
