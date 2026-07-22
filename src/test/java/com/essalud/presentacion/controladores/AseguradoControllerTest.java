package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.interfaces.IAseguradoServicio;
import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.modelo.EstadoAfiliacion;
import com.essalud.dominio.asegurado.modelo.SolicitudAfiliacion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AseguradoControllerTest {

    @Mock
    private IAseguradoServicio aseguradoServicio;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @BeforeEach
    void setUp() {
        AseguradoController controller = new AseguradoController(aseguradoServicio);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setValidator(new LocalValidatorFactoryBean())
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Nested
    class RegistrarSolicitud {

        @Test
        void shouldReturn201WhenSolicitudIsCreated() throws Exception {
            when(aseguradoServicio.registrarSolicitudAfiliacion("12345678"))
                    .thenReturn(new SolicitudAfiliacion("12345678"));

            mockMvc.perform(post("/api/asegurados/solicitud")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"dni\":\"12345678\"}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.dni").value("12345678"))
                    .andExpect(jsonPath("$.estado").value("PENDIENTE"));
        }

        @Test
        void shouldReturn400WhenDniIsMissing() throws Exception {
            mockMvc.perform(post("/api/asegurados/solicitud")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class ValidarIdentidad {

        @Test
        void shouldReturnTrueWhenDniIsValid() throws Exception {
            when(aseguradoServicio.validarIdentidadReniec("12345678")).thenReturn(true);

            mockMvc.perform(post("/api/asegurados/validar-identidad")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"dni\":\"12345678\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.dni").value("12345678"))
                    .andExpect(jsonPath("$.valido").value(true));
        }

        @Test
        void shouldReturnFalseWhenDniIsInvalid() throws Exception {
            when(aseguradoServicio.validarIdentidadReniec("00000000")).thenReturn(false);

            mockMvc.perform(post("/api/asegurados/validar-identidad")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"dni\":\"00000000\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.valido").value(false));
        }
    }

    @Nested
    class VerificarRequisitos {

        @Test
        void shouldReturnRequirementsStatus() throws Exception {
            when(aseguradoServicio.verificarRequisitosLegales("12345678")).thenReturn(true);

            mockMvc.perform(post("/api/asegurados/verificar-requisitos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"dni\":\"12345678\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cumpleRequisitos").value(true));
        }
    }

    @Nested
    class CrearAsegurado {

        @Test
        void shouldReturn201WhenAseguradoIsCreated() throws Exception {
            Asegurado asegurado = new Asegurado("12345678", "Juan", "Perez",
                    LocalDate.of(1990, 5, 15), "M");
            asegurado.setId(1L);
            when(aseguradoServicio.crearAsegurado(
                    eq("12345678"), eq("Juan"), eq("Perez"),
                    eq(LocalDate.of(1990, 5, 15)), eq("M")))
                    .thenReturn(asegurado);

            String body = objectMapper.writeValueAsString(Map.of(
                    "dni", "12345678",
                    "nombres", "Juan",
                    "apellidos", "Perez",
                    "fechaNacimiento", "1990-05-15",
                    "sexo", "M"));

            mockMvc.perform(post("/api/asegurados")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.dni").value("12345678"))
                    .andExpect(jsonPath("$.nombres").value("Juan"));
        }
    }

    @Nested
    class ObtenerAsegurado {

        @Test
        void shouldReturn200WhenAseguradoExists() throws Exception {
            Asegurado asegurado = new Asegurado("12345678", "Juan", "Perez",
                    LocalDate.of(1990, 5, 15), "M");
            asegurado.setId(1L);
            when(aseguradoServicio.obtenerDatosAsegurado("12345678")).thenReturn(asegurado);

            mockMvc.perform(get("/api/asegurados/12345678"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.dni").value("12345678"))
                    .andExpect(jsonPath("$.nombres").value("Juan"));
        }

        @Test
        void shouldReturn404WhenAseguradoNotFound() throws Exception {
            when(aseguradoServicio.obtenerDatosAsegurado("99999999"))
                    .thenThrow(new IllegalArgumentException("No se encontró asegurado con DNI 99999999"));

            mockMvc.perform(get("/api/asegurados/99999999"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class ActualizarEstado {

        @Test
        void shouldReturn200WhenEstadoIsUpdated() throws Exception {
            Asegurado asegurado = new Asegurado("12345678", "Juan", "Perez",
                    LocalDate.of(1990, 5, 15), "M");
            asegurado.setId(1L);
            asegurado.setEstadoAfiliacion(EstadoAfiliacion.ACTIVO);
            when(aseguradoServicio.actualizarEstadoAfiliacion(1L, EstadoAfiliacion.ACTIVO))
                    .thenReturn(asegurado);

            mockMvc.perform(put("/api/asegurados/1/estado")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"estado\":\"ACTIVO\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.estadoAfiliacion").value("ACTIVO"));
        }
    }
}
