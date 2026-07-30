package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.diagnostico.modelo.Diagnostico;
import com.essalud.dominio.diagnostico.repositorio.IDiagnosticoRepositorio;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DiagnosticoRepositorioImpl implements IDiagnosticoRepositorio {

    private final ConcurrentHashMap<Long, Diagnostico> almacen = new ConcurrentHashMap<>();
    private final AtomicLong contador = new AtomicLong(1);

    @Override
    public Diagnostico guardar(Diagnostico diagnostico) {
        if (diagnostico.getId() == null) {
            diagnostico.setId(contador.getAndIncrement());
        }
        almacen.put(diagnostico.getId(), diagnostico);
        return diagnostico;
    }

    @Override
    public Optional<Diagnostico> buscarPorId(Long id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public List<Diagnostico> listarTodos() {
        return new ArrayList<>(almacen.values());
    }
}
