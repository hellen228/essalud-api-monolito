package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.cita.modelo.Cita;
import com.essalud.dominio.cita.repositorio.ICitaRepositorio;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class CitaRepositorioImpl implements ICitaRepositorio {

    private final List<Cita> citas = new ArrayList<>();
    private final AtomicInteger secuenciaId = new AtomicInteger(1);

    @Override
    public Cita guardar(Cita cita) {

        if (cita.getIdCita() == null) {
            cita.setIdCita(secuenciaId.getAndIncrement());
            citas.add(cita);
            return cita;
        }

        for (int i = 0; i < citas.size(); i++) {
            Cita citaGuardada = citas.get(i);

            if (citaGuardada.getIdCita().equals(cita.getIdCita())) {
                citas.set(i, cita);
                return cita;
            }
        }

        citas.add(cita);
        return cita;
    }

    @Override
    public Optional<Cita> buscarPorId(Integer idCita) {
        return citas.stream()
                .filter(cita -> cita.getIdCita().equals(idCita))
                .findFirst();
    }

    @Override
    public List<Cita> buscarPorDniPaciente(String dniPaciente) {
        return citas.stream()
                .filter(cita -> cita.getDniPaciente().equals(dniPaciente))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> listarTodas() {
        return new ArrayList<>(citas);
    }
}