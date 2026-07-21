package com.essalud.presentacion.dto;

public record CoberturaResponseDTO(
        String dni,
        boolean seguroActivo,
        String mensaje
) {}