package com.essalud.aplicacion.servicios;

import com.essalud.dominio.farmacia.modelo.Medicamento;
import com.essalud.dominio.farmacia.repositorio.IMedicamentoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class FarmaciaServicioImplTest {

    private IMedicamentoRepositorio repositorioMock;
    private FarmaciaServicioImpl farmaciaServicio;

    @BeforeEach
    void setUp() {
        repositorioMock = Mockito.mock(IMedicamentoRepositorio.class);
        farmaciaServicio = new FarmaciaServicioImpl(repositorioMock);
    }

    @Test
    void consultarDatosKardex_Exito() {
        Medicamento medicamentoMock = new Medicamento(1, "Paracetamol 500mg", 500, false);
        when(repositorioMock.buscarPorId(1)).thenReturn(Optional.of(medicamentoMock));

        Medicamento resultado = farmaciaServicio.consultarDatosKardex(1);

        assertNotNull(resultado);
        assertEquals("Paracetamol 500mg", resultado.getNombreFarmaco());
        assertEquals(500, resultado.getStockActual());
    }

    @Test
    void consultarDatosKardex_NoEncontrado_LanzaExcepcion() {
        when(repositorioMock.buscarPorId(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            farmaciaServicio.consultarDatosKardex(99);
        });

        assertEquals("Medicamento no encontrado en el Kárdex", exception.getMessage());
    }
}