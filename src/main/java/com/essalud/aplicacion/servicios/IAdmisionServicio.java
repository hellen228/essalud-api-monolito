package com.essalud.aplicacion.servicios;

import com.essalud.dominio.admision.modelo.Admision;
import com.essalud.presentacion.dto.AdmisionRequestDTO;
import java.util.List;

public interface IAdmisionServicio {
    Admision registrarAdmision(AdmisionRequestDTO dto);
    Admision obtenerPorId(Integer id);
    List<Admision> listarPorAsegurado(Integer aseguradoId);
}