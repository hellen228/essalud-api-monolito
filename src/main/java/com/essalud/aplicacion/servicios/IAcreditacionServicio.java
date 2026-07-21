package com.essalud.aplicacion.servicios;

import com.essalud.presentacion.dto.CoberturaResponseDTO;

public interface IAcreditacionServicio {
    CoberturaResponseDTO validarCobertura(String dni);
}