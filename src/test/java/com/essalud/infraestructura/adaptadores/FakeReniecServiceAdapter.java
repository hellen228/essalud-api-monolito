package com.essalud.infraestructura.adaptadores;

import com.essalud.aplicacion.interfaces.IReniecServicio;

public class FakeReniecServiceAdapter implements IReniecServicio {

    private final String dniValido;

    public FakeReniecServiceAdapter() {
        this.dniValido = "12345678";
    }

    public FakeReniecServiceAdapter(String dniValido) {
        this.dniValido = dniValido;
    }

    @Override
    public boolean validarIdentidad(String dni) {
        return dniValido.equals(dni);
    }
}
