package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.servicios.IAdmisionServicio;
import com.essalud.dominio.admision.modelo.Admision;
import com.essalud.presentacion.dto.AdmisionRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admisiones")
public class AdmisionController {

    private final IAdmisionServicio admisionServicio;

    public AdmisionController(IAdmisionServicio admisionServicio) {
        this.admisionServicio = admisionServicio;
    }

    @PostMapping
    public ResponseEntity<Admision> registrar(@RequestBody AdmisionRequestDTO dto) {
        Admision nuevaAdmision = admisionServicio.registrarAdmision(dto);
        return new ResponseEntity<>(nuevaAdmision, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admision> obtenerPorId(@PathVariable Integer id) {
        try {
            Admision admision = admisionServicio.obtenerPorId(id);
            return ResponseEntity.ok(admision);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/asegurado/{aseguradoId}")
    public ResponseEntity<List<Admision> > listarPorAsegurado(@PathVariable Integer aseguradoId) {
        return ResponseEntity.ok(admisionServicio.listarPorAsegurado(aseguradoId));
    }
}