package org.pragma.foodcourtmanager.application.util;

import java.util.Random;

public class GeneratorPin{

    public static String generateSecurityPin(int pinLength) {
        Random random = new Random();

        StringBuilder pin = new StringBuilder();
        for (int i = 0; i < pinLength; i++) {
            int digit = random.nextInt(10);
            pin.append(digit);
        }

        return pin.toString();
    }
}
