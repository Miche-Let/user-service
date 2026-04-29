package com.michelet.user.presentation.support;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class TokenCookieProvider {

    private static final String REFRESH_TOKEN_COOKIE = "refreshToken";
    private final long refreshTokenExpirationSeconds;
    private final boolean secure;
    private final String sameSite;
    private final String path;

    public TokenCookieProvider(
        @Value("${jwt.refresh-token-expiration-seconds}") long refreshTokenExpirationSeconds,
        @Value("${cookie.refresh-token.secure}") boolean secure,
        @Value("${cookie.refresh-token.same-site}") String sameSite,
        @Value("${cookie.refresh-token.path}") String path
    ) {
        this.refreshTokenExpirationSeconds = refreshTokenExpirationSeconds;
        this.secure = secure;
        this.sameSite = sameSite;
        this.path = path;
    }
    public ResponseCookie create(String refreshToken){
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE,refreshToken)
            .httpOnly(true)
            .secure(secure)
            .sameSite(sameSite)
            .path(path)
            .maxAge(Duration.ofSeconds(refreshTokenExpirationSeconds))
            .build();
    }

    public ResponseCookie delete() {
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE, "")
            .httpOnly(true)
            .secure(secure)
            .sameSite(sameSite)
            .path(path)
            .maxAge(0)
            .build();
    }
}
