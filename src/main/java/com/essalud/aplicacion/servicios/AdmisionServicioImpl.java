package com.essalud.aplicacion.servicios;

import com.essalud.dominio.admision.modelo.Admision;
import com.essalud.dominio.admision.repositorio.IAdmisionRepositorio;
import com.essalud.presentacion.dto.AdmisionRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdmisionServicioImpl implements IAdmisionServicio {

    private final IAdmisionRepositorio repositorio;

    public AdmisionServicioImpl(IAdmisionRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    @Transactional
    public Admision registrarAdmision(AdmisionRequestDTO dto) {
        // Lógica de Negocio: Si es Emergencia, citaId puede ser nulo
        Admision admision = new Admision();
        admision.setAseguradoId(dto.getAseguradoId());
        admision.setCitaId(dto.getCitaId());
        admision.setSalaId(dto.getSalaId());
        admision.setCamaId(dto.getCamaId());
        admision.setTipoIngreso(dto.getTipoIngreso());
        admision.setPrioridadTriaje(dto.getPrioridadTriaje());

        return repositorio.guardar(admision);
    }

    @Override
    @Transactional(readOnly = true)
    public Admision obtenerPorId(Integer id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Admisión no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admision> listarPorAsegurado(Integer aseguradoId) {
        return repositorio.buscarPorAsegurado(aseguradoId);
    }
}