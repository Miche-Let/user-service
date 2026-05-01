package com.michelet.user.infrastructure.security;

import com.michelet.user.application.port.TokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final SecretKey secretkey;
    private final long accessTokenExpirationSeconds;
    private final long refreshTokenExpirationSeconds;

    public JwtTokenProvider(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.access-token-expiration-seconds}") long accessTokenExpirationSeconds,
        @Value("${jwt.refresh-token-expiration-seconds}") long refreshTokenExpirationSeconds
    ){
        this.secretkey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationSeconds = accessTokenExpirationSeconds;
        this.refreshTokenExpirationSeconds = refreshTokenExpirationSeconds;
    }

    @Override
    public String createAccessToken(UUID userId, String role) {
        Instant now = Instant.now();

        return Jwts.builder()
            .subject(userId.toString())
            .claim("role",role)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(accessTokenExpirationSeconds)))
            .signWith(secretkey, Jwts.SIG.HS256)
            .compact();
    }

    @Override
    public String createRefreshToken(UUID userId) {
        Instant now = Instant.now();

        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(refreshTokenExpirationSeconds)))
            .signWith(secretkey, Jwts.SIG.HS256)
            .compact();
    }

    @Override
    public UUID getUserId(String token) {
        String subject = Jwts.parser()
            .verifyWith(secretkey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
        return UUID.fromString(subject);
    }

    @Override
    public boolean isValid(String token) {
        try{
            Jwts.parser().verifyWith(secretkey)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long getRefreshTokenExpirationSeconds() {
        return refreshTokenExpirationSeconds;
    }
}
