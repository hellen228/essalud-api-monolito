package com.essalud.infraestructura.adaptadores;

import com.essalud.aplicacion.interfaces.IReniecServicio;
import org.springframework.stereotype.Component;

@Component
public class ReniecServiceAdapter implements IReniecServicio {

    @Override
    public boolean validarIdentidad(String dni) {
        if (dni == null || dni.length() != 8) {
            return false;
        }
        try {
            Integer.parseInt(dni);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
