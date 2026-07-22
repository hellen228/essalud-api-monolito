package com.essalud.aplicacion.interfaces;

import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.modelo.EstadoAfiliacion;
import com.essalud.dominio.asegurado.modelo.SolicitudAfiliacion;

import java.time.LocalDate;

public interface IAseguradoServicio {
    SolicitudAfiliacion registrarSolicitudAfiliacion(String dni);
    
    boolean validarIdentidadReniec(String dni);
    
    boolean verificarRequisitosLegales(String dni);
    
    Asegurado crearAsegurado(String dni, String nombres, String apellidos,
                             LocalDate fechaNacimiento, String sexo);
    
    Asegurado obtenerDatosAsegurado(String dni);

    Asegurado actualizarEstadoAfiliacion(Long idAsegurado, EstadoAfiliacion estado);
}
