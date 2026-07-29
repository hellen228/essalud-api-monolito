package com.essalud.aplicacion.servicios;

import com.essalud.dominio.farmacia.modelo.Medicamento;
import java.util.List;

public interface IFarmaciaServicio {
    Medicamento consultarDatosKardex(Integer idMedicamento);
    List<Medicamento> listarMedicamentos();
}