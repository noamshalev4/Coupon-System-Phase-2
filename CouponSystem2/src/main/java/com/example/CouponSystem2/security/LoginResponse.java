package com.example.CouponSystem2.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// Represents the response returned upon successful login.
public class LoginResponse {
    private long id;
    private UUID token;
    private String email;
    private String name;
    private ClientType clientType;
    private LocalDateTime expirationTime;
}
