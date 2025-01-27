package com.example.CouponSystem2.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// Represents information associated with a user session token.
public class TokenInformation {
    private Long id;
    private String email;

    @Builder.Default
    private LocalDateTime expirationTime = LocalDateTime.now().plusDays(1);
    private ClientType clientType;
}
