package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.acreditacion.repositorio.IHistorialAportesRepositorio;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public class HistorialAportesRepositorioImpl implements IHistorialAportesRepositorio {

    // idAsegurado con aportes al día (dato simulado)
    private final Set<Integer> aseguradosConAportesAlDia = Set.of(1);

    @Override
    public boolean tieneAportesAlDia(Integer idAsegurado) {
        return aseguradosConAportesAlDia.contains(idAsegurado);
    }
}