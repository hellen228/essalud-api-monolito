package com.essalud.infraestructura.mensajeria.farmacia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class FarmaciaRabbitConsumerTest {

    private JdbcTemplate jdbcTemplateMock;
    private FarmaciaRabbitConsumer consumer;

    @BeforeEach
    void setUp() {
        jdbcTemplateMock = Mockito.mock(JdbcTemplate.class);
        consumer = new FarmaciaRabbitConsumer(jdbcTemplateMock);
    }

    @Test
    void registrarSalida_DebeEjecutarInsertEnPostgreSQL() {
        String mensajeJson = "{\"dniPaciente\": \"72266171\", \"medicamentoEntregado\": \"Paracetamol 500mg\", \"cantidadEntregada\": 2}";

        consumer.registrarSalida(mensajeJson);

        verify(jdbcTemplateMock, times(1)).update(
                eq("INSERT INTO historial_salidas (dni_paciente, medicamento_entregado, cantidad_entregada) VALUES (?, ?, ?)"),
                eq("72266171"),
                eq("Paracetamol 500mg"),
                eq(2)
        );
    }

    @Test
    void actualizarInventario_DebeEjecutarUpdateEnPostgreSQL() {
        String mensajeJson = "{\"medicamentoRecibido\": \"Paracetamol 500mg\", \"cantidadRecibida\": 2}";

        consumer.actualizarInventario(mensajeJson);

        verify(jdbcTemplateMock, times(1)).update(
                eq("UPDATE kardex_farmacia SET stock_actual = stock_actual - ? WHERE nombre_farmaco = ?"),
                eq(2),
                eq("Paracetamol 500mg")
        );
    }
}