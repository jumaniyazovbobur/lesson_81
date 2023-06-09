package org.example.util;

import org.example.Container.ComponentContainer;

public class PhoneValidationUtil {
    public static boolean isValidPhone(String phone) {
        if (phone.length() < 12) {
            System.out.println("Enter valid phone number");
            return false;
        } else if (!phone.startsWith("998")) {
            System.out.println("Enter valid phone number");
            return false;
        }
        return true;
    }
}
