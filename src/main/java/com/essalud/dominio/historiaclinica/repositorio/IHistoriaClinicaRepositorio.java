package com.essalud.dominio.historiaclinica.repositorio;

import com.essalud.dominio.historiaclinica.modelo.HistoriaClinica;
import java.util.Optional;

public interface IHistoriaClinicaRepositorio {
    Optional<HistoriaClinica> buscarPorAseguradoId(Integer aseguradoId);
    HistoriaClinica guardar(HistoriaClinica historiaClinica);
}