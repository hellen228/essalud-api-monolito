package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.atencionmedica.modelo.AtencionMedica;
import com.essalud.dominio.atencionmedica.repositorio.IAtencionMedicaRepositorio;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AtencionMedicaRepositorioImpl implements IAtencionMedicaRepositorio {

    private final Map<Long, AtencionMedica> almacen = new ConcurrentHashMap<>();

    public AtencionMedicaRepositorioImpl() {
        AtencionMedica atencion = new AtencionMedica(1L, 1L, "EN_PROGRESO");
        almacen.put(1L, atencion);
    }

    @Override
    public Optional<AtencionMedica> buscarPorId(Long id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public AtencionMedica guardar(AtencionMedica atencion) {
        almacen.put(atencion.getId(), atencion);
        return atencion;
    }
}
