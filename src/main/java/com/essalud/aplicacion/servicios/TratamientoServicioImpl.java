package com.essalud.aplicacion.servicios;

import com.essalud.dominio.tratamiento.modelo.PlanTerapeutico;
import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.dominio.tratamiento.repositorio.IPlanTerapeuticoRepositorio;
import com.essalud.dominio.tratamiento.repositorio.IRecetaRepositorio;
import com.essalud.presentacion.dto.AgregarPrescripcionDTO;
import com.essalud.presentacion.dto.EmitirRecetaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TratamientoServicioImpl {

    private final IRecetaRepositorio recetaRepositorio;
    private final IPlanTerapeuticoRepositorio planTerapeuticoRepositorio;

    public TratamientoServicioImpl(IRecetaRepositorio recetaRepositorio,
                                   IPlanTerapeuticoRepositorio planTerapeuticoRepositorio) {
        this.recetaRepositorio = recetaRepositorio;
        this.planTerapeuticoRepositorio = planTerapeuticoRepositorio;
    }

    public PlanTerapeutico crearPlanTerapeutico(Integer diagnosticoId, String indicacionesGenerales) {
        PlanTerapeutico plan = new PlanTerapeutico();
        plan.setDiagnosticoId(diagnosticoId);
        plan.setIndicacionesGenerales(indicacionesGenerales);
        return planTerapeuticoRepositorio.guardar(plan);
    }

    public Optional<PlanTerapeutico> obtenerPlanTerapeuticoPorId(Integer id) {
        return planTerapeuticoRepositorio.buscarPorId(id);
    }

    public List<PlanTerapeutico> obtenerPlanesPorDiagnostico(Integer diagnosticoId) {
        return planTerapeuticoRepositorio.buscarPorDiagnostico(diagnosticoId);
    }

    public PlanTerapeutico actualizarPlanTerapeutico(Integer id, String indicacionesGenerales) {
        PlanTerapeutico plan = planTerapeuticoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan terapéutico no encontrado"));
        plan.setIndicacionesGenerales(indicacionesGenerales);
        return planTerapeuticoRepositorio.actualizar(plan);
    }

    public void eliminarPlanTerapeutico(Integer id) {
        planTerapeuticoRepositorio.eliminar(id);
    }

    public Receta emitirReceta(EmitirRecetaDTO dto) {
        Receta nuevaReceta = new Receta();
        nuevaReceta.setPlanTerapeuticoId(dto.getPlanTerapeuticoId());
        nuevaReceta.setMedicamentoId(dto.getMedicamentoId());
        nuevaReceta.setDosis(dto.getDosis() != null ? dto.getDosis() : "1 tableta");
        nuevaReceta.setFrecuencia(dto.getFrecuencia() != null ? dto.getFrecuencia() : "Cada 8 horas");
        nuevaReceta.setDuracion(dto.getDuracion() != null ? dto.getDuracion() : "5 dias");
        nuevaReceta.setEstadoDispensacion("Pendiente");
        return recetaRepositorio.guardar(nuevaReceta);
    }

    public Receta agregarPrescripcion(Integer planTerapeuticoId, AgregarPrescripcionDTO dto) {
        Receta receta = new Receta();
        receta.setPlanTerapeuticoId(planTerapeuticoId);
        receta.setMedicamentoId(dto.getMedicamentoId());
        receta.setDosis(dto.getDosis());
        receta.setFrecuencia(dto.getFrecuencia());
        receta.setDuracion(dto.getDuracion());
        receta.setEstadoDispensacion("Pendiente");
        return recetaRepositorio.guardar(receta);
    }

    public List<Receta> listarPrescripcionesPorPlan(Integer planTerapeuticoId) {
        return recetaRepositorio.buscarPorPlanTerapeutico(planTerapeuticoId);
    }

    public Optional<Receta> obtenerPrescripcionPorId(Integer id) {
        return recetaRepositorio.buscarPorId(id);
    }

    public Receta actualizarPrescripcion(Integer id, String dosis, String frecuencia, String duracion) {
        Receta receta = recetaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Prescripción no encontrada"));
        receta.setDosis(dosis);
        receta.setFrecuencia(frecuencia);
        receta.setDuracion(duracion);
        return recetaRepositorio.actualizar(receta);
    }

    public void eliminarPrescripcion(Integer id) {
        recetaRepositorio.eliminar(id);
    }

    public Receta cambiarEstadoDispensacion(Integer id, String estado) {
        return recetaRepositorio.actualizarEstado(id, estado);
    }

    public Receta dispensarMedicamento(Integer id) {
        return recetaRepositorio.actualizarEstado(id, "Dispensado");
    }

    public Receta cancelarPrescripcion(Integer id) {
        return recetaRepositorio.actualizarEstado(id, "Cancelado");
    }
}