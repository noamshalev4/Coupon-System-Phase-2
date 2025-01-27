package com.example.CouponSystem2.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// Represents a login request containing user credentials and client type.
public class LoginRequest {
    private String email;
    private String password;
    private ClientType clientType;
}
