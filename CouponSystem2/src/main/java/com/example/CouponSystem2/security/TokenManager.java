package com.example.CouponSystem2.security;

import com.example.CouponSystem2.exceptions.CouponSystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenManager {
    private final Map<UUID, TokenInformation> tokens;

    // Adds a new token to the token store or retrieves an existing one.
    public UUID addToken(TokenInformation tokenInformation) {
        if (tokenInformation == null)
            throw new CouponSystemException("Token information is null");

        // Implement the access of allowing login from several devices.
        if (isTokenAlreadyExists(tokenInformation)) {
            Optional<UUID> existsToken = tokens.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(tokenInformation))
                    .map(Map.Entry::getKey)
                    .findFirst();
            return existsToken.orElse(null);
        }

        // Generate a new unique token
        UUID newToken = UUID.randomUUID();
        while (tokens.containsKey(newToken)) {
            newToken = UUID.randomUUID();
        }
        tokens.put(newToken, tokenInformation);
        return newToken;
    }

    // Checks if the provided token information already exists in the token map.
    private boolean isTokenAlreadyExists(TokenInformation tokenInformation) {
        return tokens.containsValue(tokenInformation);
    }

    // Validates the token and checks its associated client type.
    public Long validateToken(UUID token, ClientType clientType) {
        TokenInformation tokenInformation = tokens.get(token);
        if (tokenInformation == null) {
            throw new CouponSystemException("Not valid token");
        }

        if (tokenInformation.getClientType() != clientType) {
            throw new CouponSystemException("Not authorized action");
        }
        tokenInformation.setExpirationTime(LocalDateTime.now().plusDays(1));
        return tokenInformation.getId();
    }

    public void deleteToken(UUID token) {
        tokens.remove(token);
    }

    // Delete expired tokens every hour.
    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 1)
    public void deleteExpiredTokens() {
        tokens.entrySet().removeIf((token) -> token.getValue().getExpirationTime().isBefore(LocalDateTime.now()));
    }
}
