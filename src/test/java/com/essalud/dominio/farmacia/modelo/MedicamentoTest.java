package com.essalud.dominio.farmacia.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentoTest {

    @Test
    void constructorYGetters_DebenFuncionarCorrectamente() {
        // Arrange & Act
        Medicamento medicamento = new Medicamento(10, "Ibuprofeno 400mg", 300, false);

        // Assert
        assertEquals(10, medicamento.getId());
        assertEquals("Ibuprofeno 400mg", medicamento.getNombreFarmaco());
        assertEquals(300, medicamento.getStockActual());
        assertFalse(medicamento.getEsControlado());
    }

    @Test
    void setters_DebenModificarValoresCorrectamente() {
        // Arrange
        Medicamento medicamento = new Medicamento(1, "Temporal", 10, true);

        // Act
        medicamento.setId(5);
        medicamento.setNombreFarmaco("Omeprazol 20mg");
        medicamento.setStockActual(120);
        medicamento.setEsControlado(false);

        // Assert
        assertEquals(5, medicamento.getId());
        assertEquals("Omeprazol 20mg", medicamento.getNombreFarmaco());
        assertEquals(120, medicamento.getStockActual());
        assertFalse(medicamento.getEsControlado());
    }
}