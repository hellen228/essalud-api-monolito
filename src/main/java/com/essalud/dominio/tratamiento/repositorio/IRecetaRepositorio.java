package com.essalud.dominio.tratamiento.repositorio;

import com.essalud.dominio.tratamiento.modelo.Receta;

public interface IRecetaRepositorio {
    Receta guardar(Receta receta);
}