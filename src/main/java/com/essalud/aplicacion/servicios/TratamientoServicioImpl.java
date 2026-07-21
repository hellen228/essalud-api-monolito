package com.essalud.aplicacion.servicios;

import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;
import com.essalud.presentacion.dto.EmitirRecetaDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TratamientoServicioImpl {

    private final IRecetaRepositorio recetaRepositorio;

    public TratamientoServicioImpl(IRecetaRepositorio recetaRepositorio) {
        this.recetaRepositorio = recetaRepositorio;
    }

    public Receta emitirReceta(EmitirRecetaDTO dto) {
        String idGenerado = "REC-" + UUID.randomUUID().toString().substring(0, 8);
        
        // Creamos el modelo asignando el estado "Pendiente" exigido en la feature
        Receta nuevaReceta = new Receta(
            idGenerado,
            dto.getDiagnosticoId(),
            dto.getMedicamento(),
            "Pendiente"
        );

        return recetaRepositorio.guardar(nuevaReceta);
    }
}