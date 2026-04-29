package com.michelet.user.presentation.support;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class TokenCookieProvider {

    private static final String REFRESH_TOKEN_COOKIE = "refreshToken";
    private final long refreshTokenExpirationSeconds;

    public TokenCookieProvider(
        @Value("${jwt.refresh-token-expiration-seconds}") long refreshTokenExpirationSeconds
    ){
        this.refreshTokenExpirationSeconds = refreshTokenExpirationSeconds;
    }
    public ResponseCookie create(String refreshToken){
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE,refreshToken)
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(Duration.ofSeconds(refreshTokenExpirationSeconds))
            .build();
    }

    public ResponseCookie delete() {
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE, "")
            .httpOnly(true)
            .secure(false)
            .sameSite("Lax")
            .path("/")
            .maxAge(0)
            .build();
    }
}
