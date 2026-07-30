package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.acreditacion.modelo.Acreditacion;
import com.essalud.dominio.acreditacion.modelo.EstadoAcreditacion;
import com.essalud.dominio.acreditacion.repositorio.IAcreditacionRepositorio;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class AcreditacionRepositorioImpl implements IAcreditacionRepositorio {

    private static final Set<Integer> ASEGURADOS_EXISTENTES = Set.of(1, 2);
    private final Map<Integer, Acreditacion> acreditaciones = new HashMap<>();

    @Override
    public Optional<Acreditacion> buscarPorIdAsegurado(Integer idAsegurado) {
        if (acreditaciones.containsKey(idAsegurado)) {
            return Optional.of(acreditaciones.get(idAsegurado));
        }
        if (ASEGURADOS_EXISTENTES.contains(idAsegurado)) {
            return Optional.of(new Acreditacion(idAsegurado, EstadoAcreditacion.PENDIENTE, null));
        }
        return Optional.empty();
    }

    @Override
    public void guardar(Acreditacion acreditacion) {
        acreditaciones.put(acreditacion.getIdAsegurado(), acreditacion);
    }
}