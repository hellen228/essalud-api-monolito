package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;

import java.util.HashMap;
import java.util.Map;

// Remueve o comenta @Repository para no usar memoria
public class RecetaRepositorioImpl implements IRecetaRepositorio {
    
    private final Map<Integer, Receta> dbMemoria = new HashMap<>(); 

    @Override
    public Receta guardar(Receta receta) {
        if (receta.getId() == null) {
            receta.setId((int) (System.currentTimeMillis() % 100000)); 
        }
        dbMemoria.put(receta.getId(), receta);
        return receta;
    }
}