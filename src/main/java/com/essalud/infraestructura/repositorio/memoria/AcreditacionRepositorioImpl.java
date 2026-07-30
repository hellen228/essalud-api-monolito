package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.acreditacion.modelo.Acreditacion;
import com.essalud.dominio.acreditacion.modelo.EstadoAcreditacion;
import com.essalud.dominio.acreditacion.repositorio.IAcreditacionRepositorio;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AcreditacionRepositorioImpl implements IAcreditacionRepositorio {

    private final Map<Integer, Acreditacion> acreditaciones = new HashMap<>();

    @Override
    public Optional<Acreditacion> buscarPorIdAsegurado(Integer idAsegurado) {
        return Optional.ofNullable(acreditaciones.get(idAsegurado))
                .or(() -> {
                    // Si no existe registro previo, se asume PENDIENTE
                    Acreditacion nueva = new Acreditacion(idAsegurado, EstadoAcreditacion.PENDIENTE, null);
                    return Optional.of(nueva);
                });
    }

    @Override
    public void guardar(Acreditacion acreditacion) {
        acreditaciones.put(acreditacion.getIdAsegurado(), acreditacion);
    }
}