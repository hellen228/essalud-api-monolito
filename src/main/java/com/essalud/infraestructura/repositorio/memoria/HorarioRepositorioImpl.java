package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.cita.modelo.HorarioMedico;
import com.essalud.dominio.cita.repositorio.IHorarioRepositorio;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HorarioRepositorioImpl implements IHorarioRepositorio {

    private final List<HorarioMedico> horarios = new ArrayList<>();

    public HorarioRepositorioImpl() {

        horarios.add(new HorarioMedico(
                1,
                101,
                "Ana Torres",
                "Cardiología",
                LocalDate.of(2026, 8, 3),
                "08:00",
                true
        ));

        horarios.add(new HorarioMedico(
                2,
                102,
                "Luis Ramos",
                "Cardiología",
                LocalDate.of(2026, 8, 3),
                "09:00",
                true
        ));

        horarios.add(new HorarioMedico(
                3,
                103,
                "Carlos Vega",
                "Cardiología",
                LocalDate.of(2026, 8, 4),
                "10:00",
                true
        ));

        horarios.add(new HorarioMedico(
                4,
                104,
                "María López",
                "Dermatología",
                LocalDate.of(2026, 8, 3),
                "11:00",
                true
        ));

        horarios.add(new HorarioMedico(
                5,
                105,
                "José Mendoza",
                "Dermatología",
                LocalDate.of(2026, 8, 4),
                "12:00",
                true
        ));

        horarios.add(new HorarioMedico(
                6,
                106,
                "Rosa Fernández",
                "Pediatría",
                LocalDate.of(2026, 8, 3),
                "14:00",
                true
        ));
    }

    @Override
    public List<HorarioMedico> buscarDisponiblesPorEspecialidadYFecha(
            String especialidad,
            LocalDate fecha) {

        return horarios.stream()
                .filter(HorarioMedico::isDisponible)
                .filter(horario ->
                        horario.getEspecialidad().equalsIgnoreCase(especialidad))
                .filter(horario ->
                        horario.getFechaDisponible().equals(fecha))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HorarioMedico> buscarPorId(Integer idHorario) {
        return horarios.stream()
                .filter(horario ->
                        horario.getIdHorario().equals(idHorario))
                .findFirst();
    }

    @Override
    public HorarioMedico guardar(HorarioMedico horarioActualizado) {

        for (int i = 0; i < horarios.size(); i++) {
            HorarioMedico horarioGuardado = horarios.get(i);

            if (horarioGuardado.getIdHorario()
                    .equals(horarioActualizado.getIdHorario())) {

                horarios.set(i, horarioActualizado);
                return horarioActualizado;
            }
        }

        horarios.add(horarioActualizado);
        return horarioActualizado;
    }

    @Override
    public List<HorarioMedico> listarTodos() {
        return new ArrayList<>(horarios);
    }
}