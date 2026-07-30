package com.essalud.dominio.diagnostico.repositorio;

import com.essalud.dominio.diagnostico.modelo.OrdenLaboratorio;
import java.util.List;
import java.util.Optional;

public interface IOrdenLaboratorioRepositorio {

    OrdenLaboratorio guardar(OrdenLaboratorio orden);

    Optional<OrdenLaboratorio> buscarPorId(Long id);

    List<OrdenLaboratorio> listarTodos();
}
