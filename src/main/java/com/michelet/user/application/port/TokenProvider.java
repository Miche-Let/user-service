package com.michelet.user.application.port;

import java.util.UUID;

public interface TokenProvider {
    String createAccessToken(UUID userId, String role);
    String createRefreshToken(UUID userId);
    UUID getUserId(String token);
    boolean isValid(String token);
    long getRefreshTokenExpirationSeconds();
}
