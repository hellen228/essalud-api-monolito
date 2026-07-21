package com.essalud.presentacion.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.essalud.aplicacion.servicios.IAcreditacionServicio;
import com.essalud.presentacion.dto.CoberturaResponseDTO;

@RestController
@RequestMapping("/api/acreditacion")
public class AcreditacionController {

    private final IAcreditacionServicio acreditacionServicio;

    public AcreditacionController(IAcreditacionServicio acreditacionServicio) {
        this.acreditacionServicio = acreditacionServicio;
    }

    @GetMapping("/{dni}")
    public ResponseEntity<CoberturaResponseDTO> consultarAcreditacion(@PathVariable String dni) {
        CoberturaResponseDTO respuesta = acreditacionServicio.validarCobertura(dni);
        return ResponseEntity.ok(respuesta);
    }
}