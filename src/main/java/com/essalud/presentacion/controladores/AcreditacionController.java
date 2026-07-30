package com.essalud.presentacion.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Acreditación", description = "Auditoría de cobertura y autorización de atención de asegurados")
public class AcreditacionController {

    private final IAcreditacionServicio acreditacionServicio;

    public AcreditacionController(IAcreditacionServicio acreditacionServicio) {
        this.acreditacionServicio = acreditacionServicio;
    }

    @Operation(summary = "Consultar cobertura de un asegurado por DNI")
    @GetMapping("/{dni}")
    public ResponseEntity<CoberturaResponseDTO> consultarAcreditacion(@PathVariable String dni) {
        CoberturaResponseDTO respuesta = acreditacionServicio.validarCobertura(dni);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Consultar vigencia del asegurado ante SUNAT")
    @GetMapping("/{dni}/vigencia-sunat")
    public ResponseEntity<Boolean> consultarVigenciaSunat(@PathVariable String dni) {
        return ResponseEntity.ok(acreditacionServicio.consultarVigenciaSunat(dni));
    }

    @Operation(summary = "Verificar si el asegurado tiene aportes al día")
    @GetMapping("/{idAsegurado}/aportes")
    public ResponseEntity<Boolean> verificarHistorialAportes(@PathVariable Integer idAsegurado) {
        return ResponseEntity.ok(acreditacionServicio.verificarHistorialAportes(idAsegurado));
    }

    @Operation(summary = "Auditar validez de la carta de garantía del asegurado")
    @GetMapping("/{idAsegurado}/carta-garantia")
    public ResponseEntity<Boolean> auditarCartaGarantia(@PathVariable Integer idAsegurado) {
        return ResponseEntity.ok(acreditacionServicio.auditarCartaGarantia(idAsegurado));
    }

    @Operation(summary = "Aprobar la cobertura de atención de un asegurado")
    @PutMapping("/{idAsegurado}/aprobar")
    public ResponseEntity<Void> aprobarCoberturaPaciente(@PathVariable Integer idAsegurado) {
        acreditacionServicio.aprobarCoberturaPaciente(idAsegurado);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Rechazar la cobertura de atención de un asegurado")
    @PutMapping("/{idAsegurado}/rechazar")
    public ResponseEntity<Void> rechazarCoberturaPaciente(@PathVariable Integer idAsegurado,
                                                          @RequestBody RechazarCoberturaRequestDTO request) {
        acreditacionServicio.rechazarCoberturaPaciente(idAsegurado, request.motivo());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Enviar notificación con el estado de acreditación del asegurado")
    @PostMapping("/{idAsegurado}/notificar")
    public ResponseEntity<Void> notificarEstadoAcreditacion(@PathVariable Integer idAsegurado) {
        acreditacionServicio.notificarEstadoAcreditacion(idAsegurado);
        return ResponseEntity.ok().build();
    }
}