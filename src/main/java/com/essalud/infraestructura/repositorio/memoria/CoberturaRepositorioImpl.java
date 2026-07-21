// infraestructura/repositorio/CoberturaRepositorioImpl.java
package com.essalud.infraestructura.repositorio.memoria;

import org.springframework.stereotype.Repository;
import com.essalud.dominio.acreditacion.modelo.Cobertura;
import com.essalud.dominio.acreditacion.repositorio.ICoberturaRepositorio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.time.Month;

@Repository
public class CoberturaRepositorioImpl implements ICoberturaRepositorio {

    private final Map<String, Cobertura> datosFalsos = new HashMap<>();

    public CoberturaRepositorioImpl() {
        datosFalsos.put("72266171",
                new Cobertura(1, "72266171", true,
                        LocalDate.of(2024, Month.JANUARY, 1), LocalDate.of(2027, Month.JANUARY, 1)));

        datosFalsos.put("70011122",
                new Cobertura(2, "70011122", false,
                        LocalDate.of(2020, Month.FEBRUARY, 1), LocalDate.of(2021, Month.FEBRUARY, 1)));
    }

    @Override
    public Optional<Cobertura> buscarPorDni(String dni) {
        return Optional.ofNullable(datosFalsos.get(dni));
    }
}