package com.essalud.aplicacion.servicios;

import com.essalud.dominio.admision.modelo.Admision;
import com.essalud.dominio.admision.repositorio.IAdmisionRepositorio;
import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.repositorio.IAseguradoRepositorio;
import com.essalud.dominio.cita.modelo.Cita;
import com.essalud.dominio.cita.repositorio.ICitaRepositorio;
import com.essalud.dominio.historiaclinica.modelo.HistoriaClinica;
import com.essalud.dominio.historiaclinica.repositorio.IHistoriaClinicaRepositorio;
import com.essalud.presentacion.dto.AdmisionRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdmisionServicioImpl implements IAdmisionServicio {

    private final IAdmisionRepositorio admisionRepositorio;
    private final IAseguradoRepositorio aseguradoRepositorio;
    private final ICitaRepositorio citaRepositorio;
    private final IHistoriaClinicaRepositorio historiaClinicaRepositorio;

    // Inyección de dependencias por constructor (Buenas Prácticas / SOLID)
    public AdmisionServicioImpl(
            IAdmisionRepositorio admisionRepositorio,
            IAseguradoRepositorio aseguradoRepositorio,
            ICitaRepositorio citaRepositorio,
            IHistoriaClinicaRepositorio historiaClinicaRepositorio) {
        this.admisionRepositorio = admisionRepositorio;
        this.aseguradoRepositorio = aseguradoRepositorio;
        this.citaRepositorio = citaRepositorio;
        this.historiaClinicaRepositorio = historiaClinicaRepositorio;
    }

    @Override
    public Admision registrarAdmision(AdmisionRequestDTO dto) {
        Asegurado asegurado = aseguradoRepositorio.buscarPorId(dto.getAseguradoId())
                .orElseThrow(() -> new IllegalArgumentException("Asegurado no encontrado en el sistema con ID/DNI: " + dto.getAseguradoId()));

        if (!asegurado.isSeguroActivo()) {
            throw new IllegalStateException("Atención rechazada: El paciente no cuenta con seguro activo en EsSalud.");
        }

        Admision nuevaAdmision = new Admision();
        nuevaAdmision.setAseguradoId(asegurado.getId());
        nuevaAdmision.setTipoIngreso(dto.getTipoIngreso());
        nuevaAdmision.setFechaIngreso(LocalDateTime.now());

        if ("Consulta Externa".equalsIgnoreCase(dto.getTipoIngreso())) {
            if (dto.getCitaId() == null) {
                throw new IllegalArgumentException("Para el ingreso por Consulta Externa es obligatorio indicar el ID de la cita.");
            }

            Cita cita = citaRepositorio.buscarPorId(dto.getCitaId())
                    .orElseThrow(() -> new IllegalArgumentException("La cita programada no existe en el sistema."));

            nuevaAdmision.setCitaId(cita.getId());
            nuevaAdmision.setSalaId(dto.getSalaId());
            nuevaAdmision.setPrioridadTriaje("IV");

        } else if ("Emergencia".equalsIgnoreCase(dto.getTipoIngreso()) || "Hospitalizacion".equalsIgnoreCase(dto.getTipoIngreso())) {
            if (dto.getPrioridadTriaje() == null || dto.getPrioridadTriaje().trim().isEmpty()) {
                throw new IllegalArgumentException("Para ingresos de Emergencia es obligatorio realizar el triaje y asignar una prioridad (Ej: I, II, III).");
            }

            nuevaAdmision.setPrioridadTriaje(dto.getPrioridadTriaje());
            nuevaAdmision.setCamaId(dto.getCamaId());
            nuevaAdmision.setCitaId(null);

        } else {
            throw new IllegalArgumentException("Tipo de ingreso no válido. Los valores permitidos son: 'Consulta Externa', 'Emergencia' o 'Hospitalizacion'.");
        }

        historiaClinicaRepositorio.buscarPorAseguradoId(asegurado.getId())
                .orElseGet(() -> {
                    HistoriaClinica nuevaHistoria = new HistoriaClinica();
                    nuevaHistoria.setAseguradoId(asegurado.getId());
                    return historiaClinicaRepositorio.guardar(nuevaHistoria);
                });

        return admisionRepositorio.guardar(nuevaAdmision);
    }

    @Override
    public Admision obtenerPorId(Integer id) {
        return admisionRepositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Admisión no encontrada con ID: " + id));
    }

    @Override
    public List<Admision> listarPorAsegurado(Integer aseguradoId) {
        return admisionRepositorio.buscarPorAsegurado(aseguradoId);
    }
}