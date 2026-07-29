package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.interfaces.IAseguradoServicio;
import com.essalud.presentacion.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/asegurados")
@Tag(name = "Asegurado", description = "Operaciones de afiliación y gestión de asegurados")
public class AseguradoController {

    private final IAseguradoServicio aseguradoServicio;

    public AseguradoController(IAseguradoServicio aseguradoServicio) {
        this.aseguradoServicio = aseguradoServicio;
    }

    @PostMapping("/solicitud")
    @Operation(summary = "Registrar solicitud de afiliación")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Solicitud creada"),
            @ApiResponse(responseCode = "400", description = "DNI inválido")
    })
    public ResponseEntity<SolicitudAfiliacionResponseDTO> registrarSolicitud(
            @Valid @RequestBody SolicitudAfiliacionRequestDTO request) {
        var solicitud = aseguradoServicio.registrarSolicitudAfiliacion(request.getDni());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SolicitudAfiliacionResponseDTO.fromDomain(solicitud));
    }

    @PostMapping("/validar-identidad")
    @Operation(summary = "Validar identidad contra RENIEC")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado de validación"),
            @ApiResponse(responseCode = "400", description = "DNI inválido")
    })
    public ResponseEntity<Map<String, Object>> validarIdentidad(
            @Valid @RequestBody ValidarIdentidadRequestDTO request) {
        boolean valido = aseguradoServicio.validarIdentidadReniec(request.getDni());
        return ResponseEntity.ok(Map.of(
                "dni", request.getDni(),
                "valido", valido));
    }

    @PostMapping("/verificar-requisitos")
    @Operation(summary = "Verificar requisitos legales del solicitante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado de verificación"),
            @ApiResponse(responseCode = "400", description = "DNI inválido")
    })
    public ResponseEntity<Map<String, Object>> verificarRequisitos(
            @Valid @RequestBody VerificarRequisitosRequestDTO request) {
        boolean cumple = aseguradoServicio.verificarRequisitosLegales(request.getDni());
        return ResponseEntity.ok(Map.of(
                "dni", request.getDni(),
                "cumpleRequisitos", cumple));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo asegurado")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Asegurado creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "DNI ya registrado")
    })
    public ResponseEntity<AseguradoResponseDTO> crearAsegurado(
            @Valid @RequestBody CrearAseguradoRequestDTO request) {
        var asegurado = aseguradoServicio.crearAsegurado(
                request.getDni(), request.getNombres(), request.getApellidos(),
                request.getFechaNacimiento(), request.getSexo());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AseguradoResponseDTO.fromDomain(asegurado));
    }

    @GetMapping("/{dni}")
    @Operation(summary = "Obtener datos de un asegurado por DNI")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos del asegurado"),
            @ApiResponse(responseCode = "404", description = "Asegurado no encontrado")
    })
    public ResponseEntity<AseguradoResponseDTO> obtenerAsegurado(
            @Parameter(description = "DNI del asegurado", example = "12345678")
            @PathVariable String dni) {
        var asegurado = aseguradoServicio.obtenerDatosAsegurado(dni);
        return ResponseEntity.ok(AseguradoResponseDTO.fromDomain(asegurado));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de afiliación")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado actualizado"),
            @ApiResponse(responseCode = "404", description = "Asegurado no encontrado")
    })
    public ResponseEntity<AseguradoResponseDTO> actualizarEstado(
            @Parameter(description = "ID del asegurado", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ActualizarEstadoRequestDTO request) {
        var asegurado = aseguradoServicio.actualizarEstadoAfiliacion(id, request.getEstado());
        return ResponseEntity.ok(AseguradoResponseDTO.fromDomain(asegurado));
    }
}
