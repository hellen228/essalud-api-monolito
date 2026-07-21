package com.essalud.aplicacion.servicios;

import com.essalud.dominio.farmacia.modelo.Medicamento;
import com.essalud.dominio.farmacia.repositorio.IMedicamentoRepositorio;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaServicioImpl implements IFarmaciaServicio {

    private final IMedicamentoRepositorio medicamentoRepositorio;

    // Inyección de dependencias
    public FarmaciaServicioImpl(IMedicamentoRepositorio medicamentoRepositorio) {
        this.medicamentoRepositorio = medicamentoRepositorio;
    }

    @Override
    public Medicamento consultarDatosKardex(Integer idMedicamento) {
        return medicamentoRepositorio.buscarPorId(idMedicamento)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado en el Kárdex"));
    }
}