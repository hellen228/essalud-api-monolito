package com.essalud.dominio.acreditacion.repositorio;

import com.essalud.dominio.acreditacion.modelo.Acreditacion;
import java.util.Optional;

public interface IAcreditacionRepositorio {
    Optional<Acreditacion> buscarPorIdAsegurado(Integer idAsegurado);
    void guardar(Acreditacion acreditacion);
}