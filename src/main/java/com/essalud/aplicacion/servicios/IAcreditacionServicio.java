package com.essalud.aplicacion.servicios;

import com.essalud.presentacion.dto.CoberturaResponseDTO;

public interface IAcreditacionServicio {
    CoberturaResponseDTO validarCobertura(String dni);
    boolean verificarHistorialAportes(Integer idAsegurado);
    boolean auditarCartaGarantia(Integer idAsegurado);
}