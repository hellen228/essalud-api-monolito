// dominio/acreditacion/repositorio/ICoberturaRepositorio.java
package com.essalud.dominio.acreditacion.repositorio;

import com.essalud.dominio.acreditacion.modelo.Cobertura;
import java.util.Optional;

public interface ICoberturaRepositorio {
    Optional<Cobertura> buscarPorDni(String dni);
}