package com.essalud.aplicacion.servicios;

import com.essalud.aplicacion.interfaces.IAseguradoServicio;
import com.essalud.aplicacion.interfaces.IReniecServicio;
import com.essalud.dominio.asegurado.modelo.Asegurado;
import com.essalud.dominio.asegurado.modelo.EstadoAfiliacion;
import com.essalud.dominio.asegurado.modelo.SolicitudAfiliacion;
import com.essalud.dominio.asegurado.repositorio.IAseguradoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AseguradoServicioImpl implements IAseguradoServicio {

    private final IAseguradoRepositorio aseguradoRepositorio;
    private final IReniecServicio reniecServicio;

    public AseguradoServicioImpl(IAseguradoRepositorio aseguradoRepositorio,
                                  IReniecServicio reniecServicio) {
        this.aseguradoRepositorio = aseguradoRepositorio;
        this.reniecServicio = reniecServicio;
    }

    /**
     * @throws IllegalArgumentException
     */
    @Override
    @Transactional
    public SolicitudAfiliacion registrarSolicitudAfiliacion(String dni) {
        if (dni == null || dni.length() != 8) {
            throw new IllegalArgumentException("DNI debe tener 8 dígitos");
        }
        SolicitudAfiliacion solicitud = new SolicitudAfiliacion(dni);
        return solicitud;
    }

    @Override
    public boolean validarIdentidadReniec(String dni) {
        return reniecServicio.validarIdentidad(dni);
    }

    @Override
    public boolean verificarRequisitosLegales(String dni) {
        if (dni == null || dni.length() != 8) {
            return false;
        }
        try {
            int suma = 0;
            for (char c : dni.toCharArray()) {
                suma += Character.getNumericValue(c);
            }
            return suma > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * @throws IllegalStateException
     */
    @Override
    @Transactional
    public Asegurado crearAsegurado(String dni, String nombres, String apellidos,
                                    LocalDate fechaNacimiento, String sexo) {
        if (aseguradoRepositorio.findByDni(dni).isPresent()) {
            throw new IllegalStateException("El DNI " + dni + " ya se encuentra registrado");
        }
        Asegurado asegurado = new Asegurado(dni, nombres, apellidos, fechaNacimiento, sexo);
        return aseguradoRepositorio.save(asegurado);
    }

    /*
     * @throws IllegalArgumentException
     */
    @Override
    @Transactional(readOnly = true)
    public Asegurado obtenerDatosAsegurado(String dni) {
        return aseguradoRepositorio.findByDni(dni)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró asegurado con DNI " + dni));
    }

    /*
     * @throws IllegalArgumentException
     */
    @Override
    @Transactional
    public Asegurado actualizarEstadoAfiliacion(Long idAsegurado, EstadoAfiliacion estado) {
        Asegurado asegurado = aseguradoRepositorio.findById(idAsegurado)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró asegurado con id " + idAsegurado));
        asegurado.setEstadoAfiliacion(estado);
        return aseguradoRepositorio.save(asegurado);
    }
}
