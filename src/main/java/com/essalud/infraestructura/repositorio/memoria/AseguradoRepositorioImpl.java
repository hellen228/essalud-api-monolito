package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.repositorio.IAseguradoRepositorio;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class AseguradoRepositorioImpl implements IAseguradoRepositorio {

    private final ConcurrentHashMap<String, Asegurado> almacen = new ConcurrentHashMap<>();

    @Override
    public Asegurado save(Asegurado asegurado) {
        if (asegurado.getDni() == null) {
            throw new IllegalArgumentException("El asegurado debe tener un DNI");
        }
        almacen.put(asegurado.getDni(), asegurado);
        return asegurado;
    }

    @Override
    public Optional<Asegurado> findById(String id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public Optional<Asegurado> findByDni(String dni) {
        return almacen.values().stream()
                .filter(a -> a.getDni().equals(dni))
                .findFirst();
    }

    @Override
    public List<Asegurado> findAll() {
        return new ArrayList<>(almacen.values());
    }

    @Override
    public void deleteById(String id) {
        almacen.remove(id);
    }
}
    