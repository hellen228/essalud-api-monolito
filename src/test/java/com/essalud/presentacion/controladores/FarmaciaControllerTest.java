package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.servicios.IFarmaciaServicio;
import com.essalud.dominio.farmacia.modelo.Medicamento;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FarmaciaController.class)
class FarmaciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFarmaciaServicio farmaciaServicio;

    @Test
    void consultarStock_Exito() throws Exception {
        Medicamento medicamento = new Medicamento(1, "Paracetamol 500mg", 500, false);
        Mockito.when(farmaciaServicio.consultarDatosKardex(1)).thenReturn(medicamento);

        mockMvc.perform(get("/api/farmacia/stock/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMedicamento").value(1))
                .andExpect(jsonPath("$.nombreFarmaco").value("Paracetamol 500mg"))
                .andExpect(jsonPath("$.stockActual").value(500))
                .andExpect(jsonPath("$.mensaje").value("Stock consultado con éxito"));
    }

    @Test
    void consultarStock_NoEncontrado() throws Exception {
        Mockito.when(farmaciaServicio.consultarDatosKardex(anyInt()))
                .thenThrow(new RuntimeException("Medicamento no encontrado en el Kárdex"));

        mockMvc.perform(get("/api/farmacia/stock/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Medicamento no encontrado en el Kárdex"));
    }
}