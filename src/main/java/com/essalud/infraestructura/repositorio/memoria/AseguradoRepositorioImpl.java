package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.repositorio.IAseguradoRepositorio;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AseguradoRepositorioImpl implements IAseguradoRepositorio {
    private final Map<Integer, Asegurado> aseguradosMock = new HashMap<>();

    public AseguradoRepositorioImpl() {
        // Datos falsos para probar el flujo de Bonita en Postman
        aseguradosMock.put(1, new Asegurado(1, "72266171", true));  // Seguro Activo
        aseguradosMock.put(2, new Asegurado(2, "70011122", false)); // Seguro Inactivo (Debe ser rechazado)
    }

    @Override
    public Optional<Asegurado> buscarPorId(Integer id) {
        return Optional.ofNullable(aseguradosMock.get(id));
    }

    @Override
    public Optional<Asegurado> buscarPorDni(String dni) {
        return aseguradosMock.values().stream()
                .filter(a -> a.getDni().equals(dni))
                .findFirst();
    }
}
=======
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

    private final ConcurrentHashMap<Long, Asegurado> almacen = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    @Override
    public Asegurado save(Asegurado asegurado) {
        if (asegurado.getId() == null) {
            asegurado.setId(secuencia.getAndIncrement());
        }
        almacen.put(asegurado.getId(), asegurado);
        return asegurado;
    }

    @Override
    public Optional<Asegurado> findById(Long id) {
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
    public void deleteById(Long id) {
        almacen.remove(id);
    }
}
>>>>>>> feature/asegurado-service
