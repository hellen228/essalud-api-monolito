package com.essalud.aplicacion.servicios;

import com.essalud.dominio.acreditacion.excepcion.AseguradoNoAcreditadoException;
import com.essalud.dominio.acreditacion.modelo.Acreditacion;
import com.essalud.dominio.acreditacion.modelo.EstadoAcreditacion;
import com.essalud.dominio.acreditacion.repositorio.IAcreditacionRepositorio;
import com.essalud.dominio.acreditacion.repositorio.ICartaGarantiaRepositorio;
import com.essalud.dominio.acreditacion.repositorio.IHistorialAportesRepositorio;
import org.springframework.stereotype.Service;
import com.essalud.dominio.acreditacion.modelo.Cobertura;
import com.essalud.dominio.acreditacion.repositorio.ICoberturaRepositorio;
import com.essalud.presentacion.dto.CoberturaResponseDTO;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AcreditacionServicioImpl implements IAcreditacionServicio {

    private final ICoberturaRepositorio coberturaRepositorio;
    private final IHistorialAportesRepositorio historialAportesRepositorio;
    private final ICartaGarantiaRepositorio cartaGarantiaRepositorio;
    Logger logger = Logger.getLogger(getClass().getName());
    private final IAcreditacionRepositorio acreditacionRepositorio;

    public AcreditacionServicioImpl(ICoberturaRepositorio coberturaRepositorio,
                                    IHistorialAportesRepositorio historialAportesRepositorio,
                                    ICartaGarantiaRepositorio cartaGarantiaRepositorio,
                                    IAcreditacionRepositorio acreditacionRepositorio) {
        this.coberturaRepositorio = coberturaRepositorio;
        this.historialAportesRepositorio = historialAportesRepositorio;
        this.cartaGarantiaRepositorio = cartaGarantiaRepositorio;
        this.acreditacionRepositorio = acreditacionRepositorio;
    }

    @Override
    public CoberturaResponseDTO validarCobertura(String dni) {
        Optional<Cobertura> cobertura = coberturaRepositorio.buscarPorDni(dni);

        if (cobertura.isEmpty()) {
            return new CoberturaResponseDTO(dni, false, "Asegurado no encontrado");
        }

        boolean activo = cobertura.get().isEstado();
        String mensaje = activo ? "Cobertura vigente" : "Cobertura vencida o inactiva";
        return new CoberturaResponseDTO(dni, activo, mensaje);
    }

    @Override
    public boolean verificarHistorialAportes(Integer idAsegurado) {
        return historialAportesRepositorio.tieneAportesAlDia(idAsegurado);
    }

    @Override
    public boolean auditarCartaGarantia(Integer idAsegurado) {
        return cartaGarantiaRepositorio.tieneCartaGarantiaValida(idAsegurado);
    }

    @Override
    public void aprobarCoberturaPaciente(Integer idAsegurado) {
        Acreditacion acreditacion = acreditacionRepositorio.buscarPorIdAsegurado(idAsegurado)
                .orElseThrow(() -> new AseguradoNoAcreditadoException(idAsegurado));
        acreditacion.setEstado(EstadoAcreditacion.APROBADO);
        acreditacion.setMotivoRechazo(null);
        acreditacionRepositorio.guardar(acreditacion);
    }

    @Override
    public void rechazarCoberturaPaciente(Integer idAsegurado, String motivo) {
        Acreditacion acreditacion = acreditacionRepositorio.buscarPorIdAsegurado(idAsegurado)
                .orElseThrow(() -> new AseguradoNoAcreditadoException(idAsegurado));
        acreditacion.setEstado(EstadoAcreditacion.RECHAZADO);
        acreditacion.setMotivoRechazo(motivo);
        acreditacionRepositorio.guardar(acreditacion);
    }

    @Override
    public void notificarEstadoAcreditacion(Integer idAsegurado) {
        Acreditacion acreditacion = acreditacionRepositorio.buscarPorIdAsegurado(idAsegurado)
                .orElseThrow(() -> new AseguradoNoAcreditadoException(idAsegurado));
        // Sin sistema real de notificaciones aún (email/SMS) -> se deja como log,
        // listo para conectar un NotificacionAdapter más adelante.
        logger.log(Level.INFO, "Notificación: asegurado {0} - estado de acreditación: {1}{2}", new Object[]{
                idAsegurado,
                acreditacion.getEstado(),
                acreditacion.getMotivoRechazo() != null ? " (Motivo: " + acreditacion.getMotivoRechazo() + ")" : ""
        });
    }
}