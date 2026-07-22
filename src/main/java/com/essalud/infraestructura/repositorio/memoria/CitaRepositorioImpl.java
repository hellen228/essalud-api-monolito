package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.cita.modelo.Cita;
import com.essalud.dominio.cita.repositorio.ICitaRepositorio;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository // <- ¡Esta anotación quita el error "Could not autowire"!
public class CitaRepositorioImpl implements ICitaRepositorio {
    @Override
    public Optional<Cita> buscarPorId(Integer id) {
        // Mock simple para que tus pruebas de Postman funcionen
        Cita citaMock = new Cita();
        citaMock.setId(id);
        return Optional.of(citaMock);
    }
}