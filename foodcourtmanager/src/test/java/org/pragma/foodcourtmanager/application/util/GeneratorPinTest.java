package org.pragma.foodcourtmanager.application.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorPinTest{

    @Test
    public void testGenerateSecurityPin() {
        int pinLength = 6;

        String generatedPin = GeneratorPin.generateSecurityPin(pinLength);

        assertNotNull(generatedPin);
        assertEquals(pinLength, generatedPin.length());
        assertTrue(generatedPin.matches("\\d+"));
    }

}