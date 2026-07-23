package com.essalud.presentacion.controladores;

import com.essalud.aplicacion.servicios.TratamientoServicioImpl;
import com.essalud.dominio.tratamiento.modelo.Receta;
import com.essalud.presentacion.dto.EmitirRecetaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tratamiento")
public class TratamientoController {

    private final TratamientoServicioImpl tratamientoServicio;

    public TratamientoController(TratamientoServicioImpl tratamientoServicio) {
        this.tratamientoServicio = tratamientoServicio;
    }

    @PostMapping("/receta")
    public ResponseEntity<Receta> emitirReceta(@RequestBody EmitirRecetaDTO dto) {
        Receta recetaCreada = tratamientoServicio.emitirReceta(dto);
        return new ResponseEntity<>(recetaCreada, HttpStatus.CREATED); // Retorna 201 Created
    }
}