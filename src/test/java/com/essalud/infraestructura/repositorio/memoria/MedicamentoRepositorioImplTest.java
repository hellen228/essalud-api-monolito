package com.essalud.infraestructura.repositorio.memoria;

import com.essalud.dominio.farmacia.modelo.Medicamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentoRepositorioImplTest {

    private MedicamentoRepositorioImpl repositorio;

    @BeforeEach
    void setUp() {
        repositorio = new MedicamentoRepositorioImpl();
    }

    @Test
    void buscarPorId_DebeRetornarMedicamento_CuandoExisteId() {
        // Act
        Optional<Medicamento> resultado = repositorio.buscarPorId(1);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getId());
        assertEquals("Paracetamol 500mg", resultado.get().getNombreFarmaco());
        assertEquals(500, resultado.get().getStockActual());
        assertFalse(resultado.get().getEsControlado());
    }

    @Test
    void buscarPorId_DebeRetornarMedicamentoControlado_CuandoEsIdDos() {
        // Act
        Optional<Medicamento> resultado = repositorio.buscarPorId(2);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(2, resultado.get().getId());
        assertEquals("Diazepam 10mg", resultado.get().getNombreFarmaco());
        assertEquals(150, resultado.get().getStockActual());
        assertTrue(resultado.get().getEsControlado());
    }

    @Test
    void buscarPorId_DebeRetornarVacio_CuandoNoExisteId() {
        // Act
        Optional<Medicamento> resultado = repositorio.buscarPorId(999);

        // Assert
        assertFalse(resultado.isPresent());
    }
}