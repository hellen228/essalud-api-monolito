package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.admision.modelo.Admision;
import com.essalud.dominio.admision.repositorio.IAdmisionRepositorio;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AdmisionRepositorioImpl implements IAdmisionRepositorio {

    // Simulación de Base de Datos en Memoria
    private final Map<Integer, Admision> admisionesMock = new HashMap<>();
    private final AtomicInteger generadorId = new AtomicInteger(1); // Para autoincrementar el ID

    @Override
    public Admision guardar(Admision admision) {
        if (admision.getId() == null) {
            admision.setId(generadorId.getAndIncrement());
        }
        admisionesMock.put(admision.getId(), admision);
        return admision;
    }

    @Override
    public Optional<Admision> buscarPorId(Integer id) {
        return Optional.ofNullable(admisionesMock.get(id));
    }

    @Override
    public List<Admision> buscarPorAsegurado(Integer aseguradoId) {
        List<Admision> resultado = new ArrayList<>();
        for (Admision admision : admisionesMock.values()) {
            if (admision.getAseguradoId().equals(aseguradoId)) {
                resultado.add(admision);
            }
        }
        return resultado;
    }

    @Override
    public List<Admision> listarTodos() {
        return new ArrayList<>(admisionesMock.values());
    }
}