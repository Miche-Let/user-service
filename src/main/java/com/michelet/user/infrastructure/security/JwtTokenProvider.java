package com.michelet.user.infrastructure.security;

import com.michelet.user.application.port.TokenProvider;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements TokenProvider {
    @Override
    public String createAccessToken(UUID userId, String role) {
        return "";
    }

    @Override
    public String createRefreshToken(UUID userId) {
        return "";
    }

    @Override
    public UUID getUserId(String token) {
        return null;
    }

    @Override
    public boolean isValid(String token) {
        return false;
    }

    @Override
    public long getRefreshTokenExpirationSeconds() {
        return 0;
    }
}
