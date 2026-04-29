package com.michelet.user.application.port;

import java.time.Duration;
import java.util.UUID;

public interface RefreshTokenStore {
    void save(UUID userId, String refreshToken, Duration ttl);
}
