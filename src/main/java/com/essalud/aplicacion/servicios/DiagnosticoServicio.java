package com.essalud.aplicacion.servicios;

import com.essalud.dominio.atencionmedica.repositorio.IAtencionMedicaRepositorio;
import com.essalud.dominio.diagnostico.modelo.Diagnostico;
import com.essalud.dominio.diagnostico.modelo.OrdenLaboratorio;
import com.essalud.dominio.diagnostico.repositorio.IDiagnosticoRepositorio;
import com.essalud.dominio.diagnostico.repositorio.IOrdenLaboratorioRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiagnosticoServicio {

    private final IDiagnosticoRepositorio diagnosticoRepositorio;
    private final IOrdenLaboratorioRepositorio ordenLaboratorioRepositorio;
    private final IAtencionMedicaRepositorio atencionMedicaRepositorio;

    public DiagnosticoServicio(IDiagnosticoRepositorio diagnosticoRepositorio,
                                IOrdenLaboratorioRepositorio ordenLaboratorioRepositorio,
                                IAtencionMedicaRepositorio atencionMedicaRepositorio) {
        this.diagnosticoRepositorio = diagnosticoRepositorio;
        this.ordenLaboratorioRepositorio = ordenLaboratorioRepositorio;
        this.atencionMedicaRepositorio = atencionMedicaRepositorio;
    }

    public OrdenLaboratorio generarOrdenLaboratorio(Long idAtencion, String tipoExamen) {
        validarAtencion(idAtencion);
        OrdenLaboratorio orden = new OrdenLaboratorio(idAtencion, tipoExamen, null);
        return ordenLaboratorioRepositorio.guardar(orden);
    }

    public OrdenLaboratorio generarOrdenRayosX(Long idAtencion, String zonaCuerpo) {
        validarAtencion(idAtencion);
        OrdenLaboratorio orden = new OrdenLaboratorio(idAtencion, null, zonaCuerpo);
        return ordenLaboratorioRepositorio.guardar(orden);
    }

    public String visualizarResultadosExamen(Long idOrden) {
        OrdenLaboratorio orden = ordenLaboratorioRepositorio.buscarPorId(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada: " + idOrden));
        if (orden.getResultado() == null) {
            return "Examen en estado " + orden.getEstado() + ". Resultado aun no disponible.";
        }
        return orden.getResultado();
    }

    public Diagnostico asignarDiagnosticoCIE10(Long idAtencion, String codigoCie10, String descripcion) {
        validarAtencion(idAtencion);
        Diagnostico diagnostico = new Diagnostico(idAtencion, codigoCie10, descripcion);
        return diagnosticoRepositorio.guardar(diagnostico);
    }

    public Diagnostico actualizarEstadoEnfermedad(Long idDiagnostico, String estado) {
        Diagnostico diagnostico = diagnosticoRepositorio.buscarPorId(idDiagnostico)
                .orElseThrow(() -> new IllegalArgumentException("Diagnostico no encontrado: " + idDiagnostico));
        diagnostico.setEstado(estado);
        return diagnosticoRepositorio.guardar(diagnostico);
    }

    public List<Diagnostico> listarDiagnosticos() {
        return diagnosticoRepositorio.listarTodos();
    }

    private void validarAtencion(Long idAtencion) {
        atencionMedicaRepositorio.buscarPorId(idAtencion)
                .orElseThrow(() -> new IllegalArgumentException("Atencion medica no encontrada: " + idAtencion));
    }
}
