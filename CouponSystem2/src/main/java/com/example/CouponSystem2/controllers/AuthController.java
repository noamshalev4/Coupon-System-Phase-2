package com.example.CouponSystem2.controllers;

import com.example.CouponSystem2.security.LoginManager;
import com.example.CouponSystem2.security.LoginRequest;
import com.example.CouponSystem2.security.LoginResponse;
import com.example.CouponSystem2.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
@RequiredArgsConstructor

// Manages login authentication
public class AuthController {
    private final LoginManager loginManager;
    private final TokenManager tokenManager;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return loginManager.handleLogin(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
    }

    @DeleteMapping("/logout")
    public void logout(@RequestHeader("Authorization") UUID token){
        tokenManager.deleteToken(token);
    }
}
