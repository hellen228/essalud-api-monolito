package com.essalud.dominio.admision.repositorio;

import com.essalud.dominio.admision.modelo.Admision;
import java.util.List;
import java.util.Optional;

public interface IAdmisionRepositorio {
    Admision guardar(Admision admision);
    Optional<Admision> buscarPorId(Integer id);
    List<Admision> buscarPorAsegurado(Integer aseguradoId);
    List<Admision> listarTodos();
}