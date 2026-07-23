package com.essalud.dominio.asegurado.repositorio;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import java.util.Optional;

public interface IAseguradoRepositorio {
    Optional<Asegurado> buscarPorId(Integer id);
    Optional<Asegurado> buscarPorDni(String dni);
}