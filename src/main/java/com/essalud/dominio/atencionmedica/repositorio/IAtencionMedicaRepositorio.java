package com.essalud.dominio.atencionmedica.repositorio;

import com.essalud.dominio.atencionmedica.modelo.AtencionMedica;
import java.util.Optional;

public interface IAtencionMedicaRepositorio {

    Optional<AtencionMedica> buscarPorId(Long id);

    AtencionMedica guardar(AtencionMedica atencion);
}
