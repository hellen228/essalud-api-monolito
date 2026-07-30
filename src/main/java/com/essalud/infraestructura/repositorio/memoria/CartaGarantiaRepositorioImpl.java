package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.acreditacion.repositorio.ICartaGarantiaRepositorio;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public class CartaGarantiaRepositorioImpl implements ICartaGarantiaRepositorio {

    // idAsegurado con carta de garantía vigente (dato simulado)
    private final Set<Integer> aseguradosConCartaValida = Set.of(1);

    @Override
    public boolean tieneCartaGarantiaValida(Integer idAsegurado) {
        return aseguradosConCartaValida.contains(idAsegurado);
    }
}