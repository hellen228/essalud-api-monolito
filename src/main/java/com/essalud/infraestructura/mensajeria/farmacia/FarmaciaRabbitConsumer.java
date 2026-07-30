package com.essalud.infraestructura.mensajeria.farmacia;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FarmaciaRabbitConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public FarmaciaRabbitConsumer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RabbitListener(queues = "cola-kardex-salidas")
    public void registrarSalida(String mensajeJson) {
        try {
            JsonNode jsonNode = objectMapper.readTree(mensajeJson);
            String dni = jsonNode.get("dniPaciente").asText();
            String medicamento = jsonNode.get("medicamentoEntregado").asText();
            int cantidad = jsonNode.get("cantidadEntregada").asInt();

            // Aquí ejecutas tu INSERT que tenías antes
            String sql = "INSERT INTO historial_salidas (dni_paciente, medicamento_entregado, cantidad_entregada) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, dni, medicamento, cantidad);

            System.out.println("Salida registrada en BD para DNI: " + dni);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "cola-inventario")
    public void actualizarInventario(String mensajeJson) {
        try {
            JsonNode jsonNode = objectMapper.readTree(mensajeJson);
            String medicamento = jsonNode.get("medicamentoRecibido").asText();
            int cantidad = jsonNode.get("cantidadRecibida").asInt();

            // Aquí ejecutas tu UPDATE que tenías antes
            String sql = "UPDATE kardex_farmacia SET stock_actual = stock_actual - ? WHERE nombre_farmaco = ?";
            jdbcTemplate.update(sql, cantidad, medicamento);

            System.out.println("Inventario actualizado para: " + medicamento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}