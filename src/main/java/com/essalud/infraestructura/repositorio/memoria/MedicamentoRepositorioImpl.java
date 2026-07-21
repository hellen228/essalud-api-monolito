package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.farmacia.modelo.Medicamento;
import com.essalud.dominio.farmacia.repositorio.IMedicamentoRepositorio;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MedicamentoRepositorioImpl implements IMedicamentoRepositorio {

    private final Map<Integer, Medicamento> kardexMock = new HashMap<>();

    public MedicamentoRepositorioImpl() {
        kardexMock.put(1, new Medicamento(1, "Paracetamol 500mg", 500, false));
        kardexMock.put(2, new Medicamento(2, "Diazepam 10mg", 150, true));
        kardexMock.put(3, new Medicamento(3, "Amoxicilina 500mg", 0, false));
    }

    @Override
    public Optional<Medicamento> buscarPorId(Integer idMedicamento) {
        return Optional.ofNullable(kardexMock.get(idMedicamento));
    }
}