package com.essalud.infraestructura.adaptadores;

import org.springframework.stereotype.Component;
import com.essalud.dominio.acreditacion.adaptadores.ISunatServiceAdapter;

import java.util.Map;

@Component
public class SunatServiceAdapter implements ISunatServiceAdapter {

    // Datos simulados
    private final Map<String, Boolean> vigenciaFalsa = Map.of(
            "20100070970", true,   // RUC activo/habido
            "20123456789", false   // RUC de baja / no habido
    );

    @Override
    public boolean consultarVigenciaSunat(String ruc) {
        return vigenciaFalsa.getOrDefault(ruc, false);
    }
}