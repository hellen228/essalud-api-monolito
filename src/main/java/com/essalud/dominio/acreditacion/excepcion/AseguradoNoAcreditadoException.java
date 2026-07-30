package com.essalud.dominio.acreditacion.excepcion;

public class AseguradoNoAcreditadoException extends RuntimeException {
    public AseguradoNoAcreditadoException(Integer idAsegurado) {
        super("No existe registro de acreditación para el asegurado con id: " + idAsegurado);
    }
}