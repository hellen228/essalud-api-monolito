package com.essalud.aplicacion.servicios;

import com.essalud.dominio.farmacia.modelo.Medicamento;

public interface IFarmaciaServicio {
    Medicamento consultarDatosKardex(Integer idMedicamento);
}