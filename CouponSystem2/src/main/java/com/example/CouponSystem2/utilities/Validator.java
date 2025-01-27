package com.example.CouponSystem2.utilities;

public final class Validator {

    private Validator() {

    }

    // Using regex.
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@(gmail|outlook|hotmail|yahoo)\\.com$"; //Chosen the 4th popular mail suppliers.
        return email.matches(regex);
    }

    // At least 4 chars.
    public static boolean isValidPassword(String password) {
        String regex = "^.{4,}$";
        return password.matches(regex);
    }
}
