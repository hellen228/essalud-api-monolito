package com.essalud.aplicacion.servicios;

import com.essalud.aplicacion.interfaces.IReniecServicio;
import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.modelo.EstadoAfiliacion;
import com.essalud.dominio.asegurado.modelo.EstadoSolicitud;
import com.essalud.dominio.asegurado.modelo.SolicitudAfiliacion;
import com.essalud.dominio.asegurado.repositorio.IAseguradoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AseguradoServicioImplTest {

    @Mock
    private IAseguradoRepositorio aseguradoRepositorio;

    @Mock
    private IReniecServicio reniecServicio;

    private AseguradoServicioImpl servicio;

    @BeforeEach
    void setUp() {
        servicio = new AseguradoServicioImpl(aseguradoRepositorio, reniecServicio);
    }

    @Nested
    class RegistrarSolicitudAfiliacion {

        @Test
        void shouldCreateSolicitudWhenDniIsValid() {
            SolicitudAfiliacion solicitud = servicio.registrarSolicitudAfiliacion("12345678");

            assertNotNull(solicitud);
            assertEquals("12345678", solicitud.getDni());
            assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
            assertNotNull(solicitud.getFechaSolicitud());
        }

        @Test
        void shouldThrowWhenDniIsNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> servicio.registrarSolicitudAfiliacion(null));
        }

        @Test
        void shouldThrowWhenDniHasInvalidLength() {
            assertThrows(IllegalArgumentException.class,
                    () -> servicio.registrarSolicitudAfiliacion("1234"));
        }
    }

    @Nested
    class ValidarIdentidadReniec {

        @Test
        void shouldReturnTrueWhenReniecValidates() {
            when(reniecServicio.validarIdentidad("12345678")).thenReturn(true);

            boolean resultado = servicio.validarIdentidadReniec("12345678");

            assertTrue(resultado);
            verify(reniecServicio).validarIdentidad("12345678");
        }

        @Test
        void shouldReturnFalseWhenReniecRejects() {
            when(reniecServicio.validarIdentidad("00000000")).thenReturn(false);

            boolean resultado = servicio.validarIdentidadReniec("00000000");

            assertFalse(resultado);
        }
    }

    @Nested
    class VerificarRequisitosLegales {

        @Test
        void shouldReturnTrueWhenDniMeetsRequirements() {
            assertTrue(servicio.verificarRequisitosLegales("12345678"));
        }

        @Test
        void shouldReturnFalseWhenDniIsNull() {
            assertFalse(servicio.verificarRequisitosLegales(null));
        }

        @Test
        void shouldReturnFalseWhenDniHasInvalidLength() {
            assertFalse(servicio.verificarRequisitosLegales("123"));
        }
    }

    @Nested
    class CrearAsegurado {

        @Test
        void shouldCreateAndSaveAsegurado() {
            when(aseguradoRepositorio.findByDni("12345678")).thenReturn(Optional.empty());
            Asegurado saved = new Asegurado("12345678", "Juan", "Perez",
                    LocalDate.of(1990, 5, 15), "M");
            saved.setId(1L);
            when(aseguradoRepositorio.save(any(Asegurado.class))).thenReturn(saved);

            Asegurado resultado = servicio.crearAsegurado("12345678", "Juan", "Perez",
                    LocalDate.of(1990, 5, 15), "M");

            assertNotNull(resultado);
            assertEquals("12345678", resultado.getDni());
            assertEquals("Juan", resultado.getNombres());
            assertEquals("Perez", resultado.getApellidos());
            assertEquals(EstadoAfiliacion.PENDIENTE, resultado.getEstadoAfiliacion());
            verify(aseguradoRepositorio).save(any(Asegurado.class));
        }

        @Test
        void shouldThrowWhenDniAlreadyExists() {
            when(aseguradoRepositorio.findByDni("12345678"))
                    .thenReturn(Optional.of(new Asegurado()));

            assertThrows(IllegalStateException.class,
                    () -> servicio.crearAsegurado("12345678", "Juan", "Perez",
                            LocalDate.of(1990, 5, 15), "M"));
            verify(aseguradoRepositorio, never()).save(any());
        }
    }

    @Nested
    class ObtenerDatosAsegurado {

        @Test
        void shouldReturnAseguradoWhenFound() {
            Asegurado asegurado = new Asegurado("12345678", "Juan", "Perez",
                    LocalDate.of(1990, 5, 15), "M");
            when(aseguradoRepositorio.findByDni("12345678")).thenReturn(Optional.of(asegurado));

            Asegurado resultado = servicio.obtenerDatosAsegurado("12345678");

            assertNotNull(resultado);
            assertEquals("12345678", resultado.getDni());
        }

        @Test
        void shouldThrowWhenNotFound() {
            when(aseguradoRepositorio.findByDni("99999999")).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class,
                    () -> servicio.obtenerDatosAsegurado("99999999"));
        }
    }

    @Nested
    class ActualizarEstadoAfiliacion {

        @Test
        void shouldUpdateEstadoWhenAseguradoExists() {
            Asegurado asegurado = new Asegurado("12345678", "Juan", "Perez",
                    LocalDate.of(1990, 5, 15), "M");
            asegurado.setId(1L);
            when(aseguradoRepositorio.findById(1L)).thenReturn(Optional.of(asegurado));
            when(aseguradoRepositorio.save(any(Asegurado.class))).thenAnswer(i -> i.getArgument(0));

            Asegurado resultado = servicio.actualizarEstadoAfiliacion(1L, EstadoAfiliacion.ACTIVO);

            assertEquals(EstadoAfiliacion.ACTIVO, resultado.getEstadoAfiliacion());
            verify(aseguradoRepositorio).save(asegurado);
        }

        @Test
        void shouldThrowWhenAseguradoNotFound() {
            when(aseguradoRepositorio.findById(99L)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class,
                    () -> servicio.actualizarEstadoAfiliacion(99L, EstadoAfiliacion.ACTIVO));
        }
    }
}
