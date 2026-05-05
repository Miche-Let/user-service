package com.michelet.user.application.port;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenStore {
    void save(UUID userId, String refreshToken, Duration ttl);
    Optional<String> find(UUID userId);
    void delete(UUID userId);
}
