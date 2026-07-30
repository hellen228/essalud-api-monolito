package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.servicios.DiagnosticoServicio;
import com.essalud.presentacion.dto.DiagnosticoRequestDTO;
import com.essalud.presentacion.dto.DiagnosticoResponseDTO;
import com.essalud.presentacion.dto.OrdenLaboratorioRequestDTO;
import com.essalud.presentacion.dto.OrdenLaboratorioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diagnosticos")
@Tag(name = "Diagnostico", description = "Operaciones del modulo de diagnostico clinico")
public class DiagnosticoController {

    private final DiagnosticoServicio diagnosticoServicio;

    public DiagnosticoController(DiagnosticoServicio diagnosticoServicio) {
        this.diagnosticoServicio = diagnosticoServicio;
    }

    @PostMapping("/ordenes-laboratorio")
    @Operation(summary = "Generar orden de laboratorio",
               description = "Crea una orden de examen de laboratorio para una atencion medica")
    @ApiResponse(responseCode = "201", description = "Orden creada exitosamente")
    @ApiResponse(responseCode = "404", description = "Atencion medica no encontrada", content = @Content)
    public ResponseEntity<OrdenLaboratorioResponseDTO> generarOrdenLaboratorio(
            @RequestBody OrdenLaboratorioRequestDTO request) {
        OrdenLaboratorioResponseDTO orden = OrdenLaboratorioResponseDTO.fromEntity(
                diagnosticoServicio.generarOrdenLaboratorio(request.getIdAtencion(), request.getTipoExamen()));
        return ResponseEntity.status(HttpStatus.CREATED).body(orden);
    }

    @PostMapping("/ordenes-rayosx")
    @Operation(summary = "Generar orden de Rayos X",
               description = "Crea una orden de Rayos X para una atencion medica")
    @ApiResponse(responseCode = "201", description = "Orden creada exitosamente")
    @ApiResponse(responseCode = "404", description = "Atencion medica no encontrada", content = @Content)
    public ResponseEntity<OrdenLaboratorioResponseDTO> generarOrdenRayosX(
            @RequestBody OrdenLaboratorioRequestDTO request) {
        OrdenLaboratorioResponseDTO orden = OrdenLaboratorioResponseDTO.fromEntity(
                diagnosticoServicio.generarOrdenRayosX(request.getIdAtencion(), request.getZonaCuerpo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(orden);
    }

    @GetMapping("/ordenes/{id}/resultados")
    @Operation(summary = "Visualizar resultados de examen",
               description = "Obtiene el resultado de una orden de examen por su ID")
    @ApiResponse(responseCode = "200", description = "Resultado obtenido")
    @ApiResponse(responseCode = "404", description = "Orden no encontrada", content = @Content)
    public ResponseEntity<Map<String, String>> visualizarResultadosExamen(
            @Parameter(description = "ID de la orden") @PathVariable Long id) {
        String resultado = diagnosticoServicio.visualizarResultadosExamen(id);
        return ResponseEntity.ok(Map.of("resultado", resultado));
    }

    @PostMapping
    @Operation(summary = "Asignar diagnostico CIE-10",
               description = "Asigna un diagnostico CIE-10 a una atencion medica")
    @ApiResponse(responseCode = "201", description = "Diagnostico asignado exitosamente")
    @ApiResponse(responseCode = "404", description = "Atencion medica no encontrada", content = @Content)
    public ResponseEntity<DiagnosticoResponseDTO> asignarDiagnosticoCIE10(
            @RequestBody DiagnosticoRequestDTO request) {
        DiagnosticoResponseDTO diagnostico = DiagnosticoResponseDTO.fromEntity(
                diagnosticoServicio.asignarDiagnosticoCIE10(request.getIdAtencion(),
                        request.getCodigoCie10(), request.getDescripcion()));
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnostico);
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de enfermedad",
               description = "Actualiza el estado de un diagnostico (ACTIVO, RESUELTO, CRONICO)")
    @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Diagnostico no encontrado", content = @Content)
    public ResponseEntity<DiagnosticoResponseDTO> actualizarEstadoEnfermedad(
            @Parameter(description = "ID del diagnostico") @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        DiagnosticoResponseDTO diagnostico = DiagnosticoResponseDTO.fromEntity(
                diagnosticoServicio.actualizarEstadoEnfermedad(id, body.get("estado")));
        return ResponseEntity.ok(diagnostico);
    }

    @GetMapping
    @Operation(summary = "Listar diagnosticos",
               description = "Obtiene la lista de todos los diagnosticos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de diagnosticos")
    public ResponseEntity<List<DiagnosticoResponseDTO>> listarDiagnosticos() {
        List<DiagnosticoResponseDTO> diagnosticos = diagnosticoServicio.listarDiagnosticos()
                .stream().map(DiagnosticoResponseDTO::fromEntity).toList();
        return ResponseEntity.ok(diagnosticos);
    }
}
