package com.essalud.aplicacion.servicios;

import com.essalud.dominio.acreditacion.repositorio.ICartaGarantiaRepositorio;
import com.essalud.dominio.acreditacion.repositorio.IHistorialAportesRepositorio;
import org.springframework.stereotype.Service;
import com.essalud.dominio.acreditacion.modelo.Cobertura;
import com.essalud.dominio.acreditacion.repositorio.ICoberturaRepositorio;
import com.essalud.presentacion.dto.CoberturaResponseDTO;

import java.util.Optional;

@Service
public class AcreditacionServicioImpl implements IAcreditacionServicio {

    private final ICoberturaRepositorio coberturaRepositorio;
    private final IHistorialAportesRepositorio historialAportesRepositorio;
    private final ICartaGarantiaRepositorio cartaGarantiaRepositorio;

    public AcreditacionServicioImpl(ICoberturaRepositorio coberturaRepositorio,
                                    IHistorialAportesRepositorio historialAportesRepositorio,
                                    ICartaGarantiaRepositorio cartaGarantiaRepositorio) {
        this.coberturaRepositorio = coberturaRepositorio;
        this.historialAportesRepositorio = historialAportesRepositorio;
        this.cartaGarantiaRepositorio = cartaGarantiaRepositorio;
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

    @Override
    public boolean verificarHistorialAportes(Integer idAsegurado) {
        return historialAportesRepositorio.tieneAportesAlDia(idAsegurado);
    }

    @Override
    public boolean auditarCartaGarantia(Integer idAsegurado) {
        return cartaGarantiaRepositorio.tieneCartaGarantiaValida(idAsegurado);
    }
}