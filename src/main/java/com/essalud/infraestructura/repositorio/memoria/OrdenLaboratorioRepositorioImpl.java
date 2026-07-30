package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.diagnostico.modelo.OrdenLaboratorio;
import com.essalud.dominio.diagnostico.repositorio.IOrdenLaboratorioRepositorio;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrdenLaboratorioRepositorioImpl implements IOrdenLaboratorioRepositorio {

    private final ConcurrentHashMap<Long, OrdenLaboratorio> almacen = new ConcurrentHashMap<>();
    private final AtomicLong contador = new AtomicLong(1);

    @Override
    public OrdenLaboratorio guardar(OrdenLaboratorio orden) {
        if (orden.getId() == null) {
            orden.setId(contador.getAndIncrement());
        }
        almacen.put(orden.getId(), orden);
        return orden;
    }

    @Override
    public Optional<OrdenLaboratorio> buscarPorId(Long id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public List<OrdenLaboratorio> listarTodos() {
        return new ArrayList<>(almacen.values());
    }
}
