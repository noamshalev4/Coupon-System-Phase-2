package com.example.CouponSystem2.exceptions;

public class CouponSystemException extends RuntimeException {
    private String massage;

    public CouponSystemException(String massage) {
        super(massage);
    }
}
