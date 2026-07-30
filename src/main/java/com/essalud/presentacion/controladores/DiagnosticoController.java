package com.essalud.presentacion.controladores;

import com.essalud.infraestructura.adaptadores.DiagnosticoEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoEventPublisher eventPublisher;

    public DiagnosticoController(DiagnosticoEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/test")
    public ResponseEntity<Map<String, String>> testRabbit(@RequestBody Map<String, Object> body) {
        eventPublisher.publicarEvento("TEST", body);
        return ResponseEntity.ok(Map.of("mensaje", "Evento publicado en RabbitMQ"));
    }
}
