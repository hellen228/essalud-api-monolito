package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.historiaclinica.modelo.HistoriaClinica;
import com.essalud.dominio.historiaclinica.repositorio.IHistoriaClinicaRepositorio;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository // <- ¡Fundamental para Spring!
public class HistoriaClinicaRepositorioImpl implements IHistoriaClinicaRepositorio {
    @Override
    public Optional<HistoriaClinica> buscarPorAseguradoId(Integer aseguradoId) {
        return Optional.empty(); // Retorna vacío para que tu servicio cree una nueva historia por defecto
    }

    @Override
    public HistoriaClinica guardar(HistoriaClinica historiaClinica) {
        historiaClinica.setId(1); // Simula un ID generado en BD
        return historiaClinica;
    }
}