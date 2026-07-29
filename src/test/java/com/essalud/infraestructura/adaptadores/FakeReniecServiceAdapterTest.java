package com.essalud.infraestructura.adaptadores;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakeReniecServiceAdapterTest {

    @Test
    void shouldReturnTrueForConfiguredValidDni() {
        FakeReniecServiceAdapter fake = new FakeReniecServiceAdapter("12345678");
        assertTrue(fake.validarIdentidad("12345678"));
    }

    @Test
    void shouldReturnFalseForOtherDni() {
        FakeReniecServiceAdapter fake = new FakeReniecServiceAdapter("12345678");
        assertFalse(fake.validarIdentidad("00000000"));
    }

    @Test
    void shouldUseDefaultDniWhenNoArgumentConstructor() {
        FakeReniecServiceAdapter fake = new FakeReniecServiceAdapter();
        assertTrue(fake.validarIdentidad("12345678"));
    }
}
