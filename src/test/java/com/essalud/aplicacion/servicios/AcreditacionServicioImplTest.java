package com.essalud.aplicacion.servicios;

import com.essalud.dominio.acreditacion.adaptadores.ISunatServiceAdapter;
import com.essalud.dominio.acreditacion.excepcion.AseguradoNoAcreditadoException;
import com.essalud.dominio.acreditacion.modelo.Acreditacion;
import com.essalud.dominio.acreditacion.modelo.Cobertura;
import com.essalud.dominio.acreditacion.modelo.EstadoAcreditacion;
import com.essalud.dominio.acreditacion.repositorio.IAcreditacionRepositorio;
import com.essalud.dominio.acreditacion.repositorio.ICartaGarantiaRepositorio;
import com.essalud.dominio.acreditacion.repositorio.ICoberturaRepositorio;
import com.essalud.dominio.acreditacion.repositorio.IHistorialAportesRepositorio;
import com.essalud.presentacion.dto.CoberturaResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AcreditacionServicioImplTest {

    @Mock private ICoberturaRepositorio coberturaRepositorio;
    @Mock private ISunatServiceAdapter sunatServiceAdapter;
    @Mock private IHistorialAportesRepositorio historialAportesRepositorio;
    @Mock private ICartaGarantiaRepositorio cartaGarantiaRepositorio;
    @Mock private IAcreditacionRepositorio acreditacionRepositorio;

    private AcreditacionServicioImpl servicio;

    @BeforeEach
    void setUp() {
        servicio = new AcreditacionServicioImpl(
                coberturaRepositorio,
                historialAportesRepositorio,
                cartaGarantiaRepositorio,
                acreditacionRepositorio,
                sunatServiceAdapter
        );
    }

    @Nested
    class ValidarCobertura {

        @Test
        void shouldReturnActiveCoverageWhenAseguradoExistsAndEstadoTrue() {
            Cobertura cobertura = new Cobertura(1, "72266171", true,
                    LocalDate.of(2024, Month.JANUARY, 1), LocalDate.of(2027, Month.JANUARY, 1));
            when(coberturaRepositorio.buscarPorDni("72266171")).thenReturn(Optional.of(cobertura));

            CoberturaResponseDTO respuesta = servicio.validarCobertura("72266171");

            assertTrue(respuesta.seguroActivo());
            assertEquals("Cobertura vigente", respuesta.mensaje());
        }

        @Test
        void shouldReturnNotFoundMessageWhenDniDoesNotExist() {
            when(coberturaRepositorio.buscarPorDni("00000000")).thenReturn(Optional.empty());

            CoberturaResponseDTO respuesta = servicio.validarCobertura("00000000");

            assertFalse(respuesta.seguroActivo());
            assertEquals("Asegurado no encontrado", respuesta.mensaje());
        }
    }

    @Nested
    class ConsultarVigenciaSunat {

        @Test
        void shouldReturnTrueWhenSunatConfirmsVigencia() {
            when(sunatServiceAdapter.consultarVigenciaSunat("72266171")).thenReturn(true);

            boolean resultado = servicio.consultarVigenciaSunat("72266171");

            assertTrue(resultado);
            verify(sunatServiceAdapter).consultarVigenciaSunat("72266171");
        }

        @Test
        void shouldReturnFalseWhenSunatRejects() {
            when(sunatServiceAdapter.consultarVigenciaSunat("00000000")).thenReturn(false);

            boolean resultado = servicio.consultarVigenciaSunat("00000000");

            assertFalse(resultado);
        }
    }

    @Nested
    class VerificarHistorialAportes {

        @Test
        void shouldReturnTrueWhenAseguradoHasAportesAlDia() {
            when(historialAportesRepositorio.tieneAportesAlDia(1)).thenReturn(true);

            assertTrue(servicio.verificarHistorialAportes(1));
        }

        @Test
        void shouldReturnFalseWhenAseguradoHasNoAportes() {
            when(historialAportesRepositorio.tieneAportesAlDia(2)).thenReturn(false);

            assertFalse(servicio.verificarHistorialAportes(2));
        }
    }

    @Nested
    class AuditarCartaGarantia {

        @Test
        void shouldReturnTrueWhenCartaGarantiaIsValid() {
            when(cartaGarantiaRepositorio.tieneCartaGarantiaValida(1)).thenReturn(true);

            assertTrue(servicio.auditarCartaGarantia(1));
        }

        @Test
        void shouldReturnFalseWhenCartaGarantiaIsInvalid() {
            when(cartaGarantiaRepositorio.tieneCartaGarantiaValida(2)).thenReturn(false);

            assertFalse(servicio.auditarCartaGarantia(2));
        }
    }

    @Nested
    class AprobarCoberturaPaciente {

        @Test
        void shouldSetEstadoAprobadoAndClearMotivo() {
            Acreditacion acreditacion = new Acreditacion(1, EstadoAcreditacion.PENDIENTE, null);
            when(acreditacionRepositorio.buscarPorIdAsegurado(1)).thenReturn(Optional.of(acreditacion));

            servicio.aprobarCoberturaPaciente(1);

            assertEquals(EstadoAcreditacion.APROBADO, acreditacion.getEstado());
            assertNull(acreditacion.getMotivoRechazo());
            verify(acreditacionRepositorio).guardar(acreditacion);
        }

        @Test
        void shouldThrowWhenAseguradoNotFound() {
            when(acreditacionRepositorio.buscarPorIdAsegurado(999)).thenReturn(Optional.empty());

            assertThrows(AseguradoNoAcreditadoException.class,
                    () -> servicio.aprobarCoberturaPaciente(999));

            verify(acreditacionRepositorio, never()).guardar(any());
        }
    }

    @Nested
    class RechazarCoberturaPaciente {

        @Test
        void shouldSetEstadoRechazadoWithMotivo() {
            Acreditacion acreditacion = new Acreditacion(1, EstadoAcreditacion.PENDIENTE, null);
            when(acreditacionRepositorio.buscarPorIdAsegurado(1)).thenReturn(Optional.of(acreditacion));

            servicio.rechazarCoberturaPaciente(1, "Cobertura vencida");

            assertEquals(EstadoAcreditacion.RECHAZADO, acreditacion.getEstado());
            assertEquals("Cobertura vencida", acreditacion.getMotivoRechazo());
            verify(acreditacionRepositorio).guardar(acreditacion);
        }

        @Test
        void shouldThrowWhenAseguradoNotFound() {
            when(acreditacionRepositorio.buscarPorIdAsegurado(999)).thenReturn(Optional.empty());

            assertThrows(AseguradoNoAcreditadoException.class,
                    () -> servicio.rechazarCoberturaPaciente(999, "motivo cualquiera"));
        }
    }

    @Nested
    class NotificarEstadoAcreditacion {

        @Test
        void shouldNotThrowWhenAseguradoExists() {
            Acreditacion acreditacion = new Acreditacion(1, EstadoAcreditacion.APROBADO, null);
            when(acreditacionRepositorio.buscarPorIdAsegurado(1)).thenReturn(Optional.of(acreditacion));

            assertDoesNotThrow(() -> servicio.notificarEstadoAcreditacion(1));
        }

        @Test
        void shouldThrowWhenAseguradoNotFound() {
            when(acreditacionRepositorio.buscarPorIdAsegurado(999)).thenReturn(Optional.empty());

            assertThrows(AseguradoNoAcreditadoException.class,
                    () -> servicio.notificarEstadoAcreditacion(999));
        }
    }
}