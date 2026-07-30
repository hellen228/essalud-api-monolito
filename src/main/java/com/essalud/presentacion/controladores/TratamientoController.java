package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.servicios.TratamientoServicioImpl;
import com.essalud.dominio.tratamiento.modelo.PlanTerapeutico;
import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.presentacion.dto.AgregarPrescripcionDTO;
import com.essalud.presentacion.dto.ActualizarEstadoDispensacionDTO;
import com.essalud.presentacion.dto.ActualizarPrescripcionDTO;
import com.essalud.presentacion.dto.CrearPlanTerapeuticoDTO;
import com.essalud.presentacion.dto.EmitirRecetaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamiento")
public class TratamientoController {

    private final TratamientoServicioImpl tratamientoServicio;

    public TratamientoController(TratamientoServicioImpl tratamientoServicio) {
        this.tratamientoServicio = tratamientoServicio;
    }

    @PostMapping("/plan-terapeutico")
    public ResponseEntity<PlanTerapeutico> crearPlanTerapeutico(@RequestBody CrearPlanTerapeuticoDTO dto) {
        PlanTerapeutico plan = tratamientoServicio.crearPlanTerapeutico(dto.getDiagnosticoId(), dto.getIndicacionesGenerales());
        return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }

    @GetMapping("/plan-terapeutico/{id}")
    public ResponseEntity<PlanTerapeutico> obtenerPlanTerapeutico(@PathVariable Integer id) {
        return tratamientoServicio.obtenerPlanTerapeuticoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/plan-terapeutico/diagnostico/{diagnosticoId}")
    public ResponseEntity<List<PlanTerapeutico>> obtenerPlanesPorDiagnostico(@PathVariable Integer diagnosticoId) {
        return ResponseEntity.ok(tratamientoServicio.obtenerPlanesPorDiagnostico(diagnosticoId));
    }

    @PostMapping("/receta")
    public ResponseEntity<Receta> emitirReceta(@RequestBody EmitirRecetaDTO dto) {
        Receta recetaCreada = tratamientoServicio.emitirReceta(dto);
        return new ResponseEntity<>(recetaCreada, HttpStatus.CREATED);
    }

    @PostMapping("/plan-terapeutico/{planId}/prescripcion")
    public ResponseEntity<Receta> agregarPrescripcion(@PathVariable Integer planId, @RequestBody AgregarPrescripcionDTO dto) {
        Receta receta = tratamientoServicio.agregarPrescripcion(planId, dto);
        return new ResponseEntity<>(receta, HttpStatus.CREATED);
    }

    @GetMapping("/plan-terapeutico/{planId}/prescripciones")
    public ResponseEntity<List<Receta>> listarPrescripcionesPorPlan(@PathVariable Integer planId) {
        return ResponseEntity.ok(tratamientoServicio.listarPrescripcionesPorPlan(planId));
    }

    @GetMapping("/prescripciones/{id}")
    public ResponseEntity<Receta> obtenerPrescripcion(@PathVariable Integer id) {
        return tratamientoServicio.obtenerPrescripcionPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/prescripciones/{id}")
    public ResponseEntity<Receta> actualizarPrescripcion(@PathVariable Integer id, @RequestBody ActualizarPrescripcionDTO dto) {
        Receta recetaActualizada = tratamientoServicio.actualizarPrescripcion(id, dto.getDosis(), dto.getFrecuencia(), dto.getDuracion());
        return ResponseEntity.ok(recetaActualizada);
    }

    @PatchMapping("/prescripciones/{id}/estado")
    public ResponseEntity<Receta> cambiarEstadoDispensacion(@PathVariable Integer id, @RequestBody ActualizarEstadoDispensacionDTO dto) {
        Receta recetaActualizada = tratamientoServicio.cambiarEstadoDispensacion(id, dto.getEstado());
        return ResponseEntity.ok(recetaActualizada);
    }

    @PostMapping("/prescripciones/{id}/dispensar")
    public ResponseEntity<Receta> dispensarMedicamento(@PathVariable Integer id) {
        return ResponseEntity.ok(tratamientoServicio.dispensarMedicamento(id));
    }

    @PostMapping("/prescripciones/{id}/cancelar")
    public ResponseEntity<Receta> cancelarPrescripcion(@PathVariable Integer id) {
        return ResponseEntity.ok(tratamientoServicio.cancelarPrescripcion(id));
    }
}