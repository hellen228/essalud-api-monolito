package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.repositorio.IAseguradoRepositorio;
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