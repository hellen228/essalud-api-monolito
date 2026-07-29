package com.essalud.aplicacion.servicios;

import com.essalud.dominio.cita.modelo.Cita;
import com.essalud.dominio.cita.modelo.HorarioMedico;
import com.essalud.dominio.cita.repositorio.ICitaRepositorio;
import com.essalud.dominio.cita.repositorio.IHorarioRepositorio;
import com.essalud.presentacion.dto.ReservaHorarioDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CitaServicioImpl implements ICitaServicio {

    private final ICitaRepositorio citaRepositorio;
    private final IHorarioRepositorio horarioRepositorio;

    public CitaServicioImpl(
            ICitaRepositorio citaRepositorio,
            IHorarioRepositorio horarioRepositorio) {

        this.citaRepositorio = citaRepositorio;
        this.horarioRepositorio = horarioRepositorio;
    }

    @Override
    public List<HorarioMedico> consultarHorariosDisponibles(
            String especialidad,
            LocalDate fecha) {

        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new RuntimeException("La especialidad es obligatoria");
        }

        if (fecha == null) {
            throw new RuntimeException("La fecha es obligatoria");
        }

        return horarioRepositorio
                .buscarDisponiblesPorEspecialidadYFecha(
                        especialidad,
                        fecha
                );
    }

    @Override
    public Cita reservarHorario(ReservaHorarioDTO request) {

        if (request == null) {
            throw new RuntimeException("Los datos de la reserva son obligatorios");
        }

        if (request.getDniPaciente() == null
                || request.getDniPaciente().trim().isEmpty()) {

            throw new RuntimeException("El DNI del paciente es obligatorio");
        }

        if (!request.getDniPaciente().matches("\\d{8}")) {
            throw new RuntimeException("El DNI debe contener 8 números");
        }

        if (request.getIdHorario() == null) {
            throw new RuntimeException("El horario seleccionado es obligatorio");
        }

        HorarioMedico horario = horarioRepositorio
                .buscarPorId(request.getIdHorario())
                .orElseThrow(() ->
                        new RuntimeException("Horario médico no encontrado"));

        if (!horario.isDisponible()) {
            throw new RuntimeException("El horario seleccionado ya no está disponible");
        }

        /*
         * Equivale a la tarea Reservar_Horario de Bonita:
         * horarioElegidoBD.setDisponible(false)
         */
        horario.setDisponible(false);
        horarioRepositorio.guardar(horario);

        Cita cita = new Cita();

        cita.setDniPaciente(request.getDniPaciente());
        cita.setEspecialidad(horario.getEspecialidad());
        cita.setFechaCita(horario.getFechaDisponible());
        cita.setHoraCita(horario.getHoraDisponible());
        cita.setEstadoCita("PROGRAMADA");
        cita.setIdHorario(horario.getIdHorario());

        return citaRepositorio.guardar(cita);
    }

    @Override
    public Cita consultarCita(Integer idCita) {

        if (idCita == null) {
            throw new RuntimeException("El identificador de la cita es obligatorio");
        }

        return citaRepositorio.buscarPorId(idCita)
                .orElseThrow(() ->
                        new RuntimeException("Cita médica no encontrada"));
    }

    @Override
    public List<Cita> consultarCitasPorPaciente(String dniPaciente) {

        if (dniPaciente == null || dniPaciente.trim().isEmpty()) {
            throw new RuntimeException("El DNI del paciente es obligatorio");
        }

        return citaRepositorio.buscarPorDniPaciente(dniPaciente);
    }
}