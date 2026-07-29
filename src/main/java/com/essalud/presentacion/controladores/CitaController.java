package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.servicios.ICitaServicio;
import com.essalud.dominio.cita.modelo.Cita;
import com.essalud.dominio.cita.modelo.HorarioMedico;
import com.essalud.presentacion.dto.ReservaHorarioDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final ICitaServicio citaServicio;

    public CitaController(ICitaServicio citaServicio) {
        this.citaServicio = citaServicio;
    }

    @GetMapping("/horarios")
    public ResponseEntity<Map<String, Object>> consultarHorarios(
            @RequestParam String especialidad,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha) {

        try {
            List<HorarioMedico> horarios =
                    citaServicio.consultarHorariosDisponibles(
                            especialidad,
                            fecha
                    );

            List<Map<String, Object>> datos = horarios.stream()
                    .map(horario -> {
                        Map<String, Object> item = new HashMap<>();

                        item.put("idHorario", horario.getIdHorario());
                        item.put("idMedico", horario.getIdMedico());
                        item.put("nombreMedico", horario.getNombreMedico());
                        item.put("especialidad", horario.getEspecialidad());
                        item.put("fechaDisponible", horario.getFechaDisponible());
                        item.put("horaDisponible", horario.getHoraDisponible());
                        item.put("disponible", horario.getDisponible());

                        return item;
                    })
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();

            response.put("especialidad", especialidad);
            response.put("fecha", fecha);
            response.put("cantidad", datos.size());
            response.put("horarios", datos);
            response.put(
                    "mensaje",
                    datos.isEmpty()
                            ? "No existen horarios disponibles"
                            : "Horarios consultados con éxito"
            );

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return construirError(e.getMessage(), 400);
        }
    }

    /*
     * POST /api/citas/reservar
     */
    @PostMapping("/reservar")
    public ResponseEntity<Map<String, Object>> reservarHorario(
            @RequestBody ReservaHorarioDTO request) {

        try {
            Cita cita = citaServicio.reservarHorario(request);

            Map<String, Object> response = new HashMap<>();

            response.put("idCita", cita.getIdCita());
            response.put("dniPaciente", cita.getDniPaciente());
            response.put("especialidad", cita.getEspecialidad());
            response.put("fechaCita", cita.getFechaCita());
            response.put("horaCita", cita.getHoraCita());
            response.put("estadoCita", cita.getEstadoCita());
            response.put("idHorario", cita.getIdHorario());
            response.put("mensaje", "Cita médica programada con éxito");

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return construirError(e.getMessage(), 400);
        }
    }

    /*
     * GET /api/citas/1
     */
    @GetMapping("/{idCita}")
    public ResponseEntity<Map<String, Object>> consultarCita(
            @PathVariable Integer idCita) {

        try {
            Cita cita = citaServicio.consultarCita(idCita);

            Map<String, Object> response = new HashMap<>();

            response.put("idCita", cita.getIdCita());
            response.put("dniPaciente", cita.getDniPaciente());
            response.put("especialidad", cita.getEspecialidad());
            response.put("fechaCita", cita.getFechaCita());
            response.put("horaCita", cita.getHoraCita());
            response.put("estadoCita", cita.getEstadoCita());
            response.put("idHorario", cita.getIdHorario());
            response.put("mensaje", "Cita consultada con éxito");

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return construirError(e.getMessage(), 404);
        }
    }

    /*
     * GET /api/citas/paciente/12345678
     */
    @GetMapping("/paciente/{dniPaciente}")
    public ResponseEntity<Map<String, Object>> consultarCitasPaciente(
            @PathVariable String dniPaciente) {

        try {
            List<Cita> citas =
                    citaServicio.consultarCitasPorPaciente(dniPaciente);

            Map<String, Object> response = new HashMap<>();

            response.put("dniPaciente", dniPaciente);
            response.put("cantidad", citas.size());
            response.put("citas", citas);
            response.put(
                    "mensaje",
                    citas.isEmpty()
                            ? "El paciente no tiene citas registradas"
                            : "Citas consultadas con éxito"
            );

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return construirError(e.getMessage(), 400);
        }
    }

    private ResponseEntity<Map<String, Object>> construirError(
            String mensaje,
            int estadoHttp) {

        Map<String, Object> error = new HashMap<>();
        error.put("error", mensaje);

        return ResponseEntity.status(estadoHttp).body(error);
    }
}