package com.essalud.aplicacion.servicios;

import org.springframework.stereotype.Service;
import com.essalud.dominio.acreditacion.modelo.Cobertura;
import com.essalud.dominio.acreditacion.repositorio.ICoberturaRepositorio;
import com.essalud.presentacion.dto.CoberturaResponseDTO;

import java.util.Optional;

@Service
public class AcreditacionServicioImpl implements IAcreditacionServicio {

    private final ICoberturaRepositorio coberturaRepositorio;

    public AcreditacionServicioImpl(ICoberturaRepositorio coberturaRepositorio) {
        this.coberturaRepositorio = coberturaRepositorio;
    }

    @Override
    public CoberturaResponseDTO validarCobertura(String dni) {
        Optional<Cobertura> cobertura = coberturaRepositorio.buscarPorDni(dni);

        if (cobertura.isEmpty()) {
            return new CoberturaResponseDTO(dni, false, "Asegurado no encontrado");
        }

        boolean activo = cobertura.get().isEstado();
        String mensaje = activo ? "Cobertura vigente" : "Cobertura vencida o inactiva";
        return new CoberturaResponseDTO(dni, activo, mensaje);
    }
}