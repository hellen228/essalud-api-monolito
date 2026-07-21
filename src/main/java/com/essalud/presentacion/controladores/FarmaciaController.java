package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.servicios.IFarmaciaServicio;
import com.essalud.dominio.farmacia.modelo.Medicamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/farmacia")
public class FarmaciaController {

    private final IFarmaciaServicio farmaciaServicio;

    public FarmaciaController(IFarmaciaServicio farmaciaServicio) {
        this.farmaciaServicio = farmaciaServicio;
    }

    // Endpoint: GET /api/farmacia/stock/{idMedicamento}
    @GetMapping("/stock/{idMedicamento}")
    public ResponseEntity<Map<String, Object>> consultarStock(@PathVariable Integer idMedicamento) {
        try {
            Medicamento medicamento = farmaciaServicio.consultarDatosKardex(idMedicamento);

            Map<String, Object> response = new HashMap<>();
            response.put("idMedicamento", medicamento.getId());
            response.put("nombreFarmaco", medicamento.getNombreFarmaco());
            response.put("stockActual", medicamento.getStockActual());
            response.put("esControlado", medicamento.getEsControlado());
            response.put("mensaje", "Stock consultado con éxito");

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(404).body(error);
        }
    }
}