package com.essalud.aplicacion.servicios;

import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;
import com.essalud.presentacion.dto.EmitirRecetaDTO;
import org.springframework.stereotype.Service;

@Service
public class TratamientoServicioImpl {

    private final IRecetaRepositorio recetaRepositorio;

    public TratamientoServicioImpl(IRecetaRepositorio recetaRepositorio) {
        this.recetaRepositorio = recetaRepositorio;
    }

    public Receta emitirReceta(EmitirRecetaDTO dto) {
        Receta nuevaReceta = new Receta();
        // El ID lo genera PostgreSQL automáticamente por el SERIAL
        nuevaReceta.setPlanTerapeuticoId(dto.getPlanTerapeuticoId());
        nuevaReceta.setMedicamentoId(dto.getMedicamentoId());
        nuevaReceta.setDosis(dto.getDosis() != null ? dto.getDosis() : "1 tableta");
        nuevaReceta.setFrecuencia(dto.getFrecuencia() != null ? dto.getFrecuencia() : "Cada 8 horas");
        nuevaReceta.setDuracion(dto.getDuracion() != null ? dto.getDuracion() : "5 dias");
        nuevaReceta.setEstadoDispensacion("Pendiente"); // Valor por defecto

        return recetaRepositorio.guardar(nuevaReceta);
    }
}