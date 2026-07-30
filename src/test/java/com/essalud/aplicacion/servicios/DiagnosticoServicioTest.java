package com.essalud.aplicacion.servicios;

import com.essalud.dominio.atencionmedica.modelo.AtencionMedica;
import com.essalud.dominio.atencionmedica.repositorio.IAtencionMedicaRepositorio;
import com.essalud.dominio.diagnostico.modelo.Diagnostico;
import com.essalud.dominio.diagnostico.modelo.OrdenLaboratorio;
import com.essalud.dominio.diagnostico.repositorio.IDiagnosticoRepositorio;
import com.essalud.dominio.diagnostico.repositorio.IOrdenLaboratorioRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiagnosticoServicioTest {

    @Mock private IDiagnosticoRepositorio diagnosticoRepositorio;
    @Mock private IOrdenLaboratorioRepositorio ordenLaboratorioRepositorio;
    @Mock private IAtencionMedicaRepositorio atencionMedicaRepositorio;

    private DiagnosticoServicio servicio;

    @BeforeEach
    void setUp() {
        servicio = new DiagnosticoServicio(diagnosticoRepositorio, ordenLaboratorioRepositorio, atencionMedicaRepositorio);
    }

    @Test
    void generarOrdenLaboratorio_debeCrearOrden_CuandoAtencionExiste() {
        when(atencionMedicaRepositorio.buscarPorId(1L)).thenReturn(Optional.of(new AtencionMedica(1L, 1L, "EN_PROGRESO")));
        when(ordenLaboratorioRepositorio.guardar(any())).thenAnswer(i -> i.getArgument(0));

        OrdenLaboratorio resultado = servicio.generarOrdenLaboratorio(1L, "HEMATOLOGIA_COMPLETA");

        assertNotNull(resultado);
        assertEquals("HEMATOLOGIA_COMPLETA", resultado.getTipoExamen());
        assertEquals("SOLICITADO", resultado.getEstado());
        verify(ordenLaboratorioRepositorio).guardar(any());
    }

    @Test
    void generarOrdenLaboratorio_debeLanzarExcepcion_CuandoAtencionNoExiste() {
        when(atencionMedicaRepositorio.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> servicio.generarOrdenLaboratorio(99L, "HEMATOLOGIA_COMPLETA"));
        verify(ordenLaboratorioRepositorio, never()).guardar(any());
    }

    @Test
    void generarOrdenRayosX_debeCrearOrden_CuandoAtencionExiste() {
        when(atencionMedicaRepositorio.buscarPorId(1L)).thenReturn(Optional.of(new AtencionMedica(1L, 1L, "EN_PROGRESO")));
        when(ordenLaboratorioRepositorio.guardar(any())).thenAnswer(i -> i.getArgument(0));

        OrdenLaboratorio resultado = servicio.generarOrdenRayosX(1L, "TORAX_AP");

        assertNotNull(resultado);
        assertEquals("TORAX_AP", resultado.getZonaCuerpo());
        verify(ordenLaboratorioRepositorio).guardar(any());
    }

    @Test
    void visualizarResultadosExamen_debeRetornarMensaje_CuandoNoHayResultado() {
        OrdenLaboratorio orden = new OrdenLaboratorio(1L, "HEMATOLOGIA_COMPLETA", null);
        orden.setId(1L);
        when(ordenLaboratorioRepositorio.buscarPorId(1L)).thenReturn(Optional.of(orden));

        String resultado = servicio.visualizarResultadosExamen(1L);

        assertTrue(resultado.contains("SOLICITADO"));
    }

    @Test
    void visualizarResultadosExamen_debeRetornarResultado_CuandoExiste() {
        OrdenLaboratorio orden = new OrdenLaboratorio(1L, "HEMATOLOGIA_COMPLETA", null);
        orden.setId(1L);
        orden.setResultado("HEMATOCRITO: 45%");
        when(ordenLaboratorioRepositorio.buscarPorId(1L)).thenReturn(Optional.of(orden));

        String resultado = servicio.visualizarResultadosExamen(1L);

        assertEquals("HEMATOCRITO: 45%", resultado);
    }

    @Test
    void visualizarResultadosExamen_debeLanzarExcepcion_CuandoOrdenNoExiste() {
        when(ordenLaboratorioRepositorio.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> servicio.visualizarResultadosExamen(99L));
    }

    @Test
    void asignarDiagnosticoCIE10_debeAsignarDiagnostico_CuandoAtencionExiste() {
        when(atencionMedicaRepositorio.buscarPorId(1L)).thenReturn(Optional.of(new AtencionMedica(1L, 1L, "EN_PROGRESO")));
        when(diagnosticoRepositorio.guardar(any())).thenAnswer(i -> i.getArgument(0));

        Diagnostico resultado = servicio.asignarDiagnosticoCIE10(1L, "J18.9", "Neumonia");

        assertNotNull(resultado);
        assertEquals("J18.9", resultado.getCodigoCie10());
        assertEquals("ACTIVO", resultado.getEstado());
        verify(diagnosticoRepositorio).guardar(any());
    }

    @Test
    void asignarDiagnosticoCIE10_debeLanzarExcepcion_CuandoAtencionNoExiste() {
        when(atencionMedicaRepositorio.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> servicio.asignarDiagnosticoCIE10(99L, "J18.9", "Neumonia"));
        verify(diagnosticoRepositorio, never()).guardar(any());
    }

    @Test
    void actualizarEstadoEnfermedad_debeActualizarEstado() {
        Diagnostico diagnostico = new Diagnostico(1L, "J18.9", "Neumonia");
        diagnostico.setId(1L);
        when(diagnosticoRepositorio.buscarPorId(1L)).thenReturn(Optional.of(diagnostico));
        when(diagnosticoRepositorio.guardar(any())).thenAnswer(i -> i.getArgument(0));

        Diagnostico resultado = servicio.actualizarEstadoEnfermedad(1L, "RESUELTO");

        assertEquals("RESUELTO", resultado.getEstado());
    }

    @Test
    void actualizarEstadoEnfermedad_debeLanzarExcepcion_CuandoDiagnosticoNoExiste() {
        when(diagnosticoRepositorio.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> servicio.actualizarEstadoEnfermedad(99L, "RESUELTO"));
    }
}
