package com.example.CouponSystem2.controllers;

import com.example.CouponSystem2.exceptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviserController {

    // Global exception handler for all controllers
    @ExceptionHandler(CouponSystemException.class)
    public ProblemDetail handleCouponSystemException(CouponSystemException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
    // Handle all the rest
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception e){
        System.out.println(e.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Something went wrong, please try again latter");
    }
}
