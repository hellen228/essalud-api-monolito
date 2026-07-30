package com.essalud.presentacion.controladores;

import com.essalud.dominio.acreditacion.excepcion.AseguradoNoAcreditadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AseguradoNoAcreditadoException.class)
    public ResponseEntity<Map<String, String>> manejarAseguradoNoAcreditado(AseguradoNoAcreditadoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }
}