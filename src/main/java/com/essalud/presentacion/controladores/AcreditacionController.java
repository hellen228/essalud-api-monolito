package com.essalud.presentacion.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.essalud.aplicacion.servicios.IAcreditacionServicio;
import com.essalud.presentacion.dto.CoberturaResponseDTO;
import com.essalud.presentacion.dto.RechazarCoberturaRequestDTO;

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

    @GetMapping("/{idAsegurado}/aportes")
    public ResponseEntity<Boolean> verificarHistorialAportes(@PathVariable Integer idAsegurado) {
        return ResponseEntity.ok(acreditacionServicio.verificarHistorialAportes(idAsegurado));
    }

    @GetMapping("/{idAsegurado}/carta-garantia")
    public ResponseEntity<Boolean> auditarCartaGarantia(@PathVariable Integer idAsegurado) {
        return ResponseEntity.ok(acreditacionServicio.auditarCartaGarantia(idAsegurado));
    }

    @PutMapping("/{idAsegurado}/aprobar")
    public ResponseEntity<Void> aprobarCoberturaPaciente(@PathVariable Integer idAsegurado) {
        acreditacionServicio.aprobarCoberturaPaciente(idAsegurado);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idAsegurado}/rechazar")
    public ResponseEntity<Void> rechazarCoberturaPaciente(@PathVariable Integer idAsegurado,
                                                          @RequestBody RechazarCoberturaRequestDTO request) {
        acreditacionServicio.rechazarCoberturaPaciente(idAsegurado, request.motivo());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idAsegurado}/notificar")
    public ResponseEntity<Void> notificarEstadoAcreditacion(@PathVariable Integer idAsegurado) {
        acreditacionServicio.notificarEstadoAcreditacion(idAsegurado);
        return ResponseEntity.ok().build();
    }
}