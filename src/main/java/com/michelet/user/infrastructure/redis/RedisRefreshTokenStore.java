package com.michelet.user.infrastructure.redis;

import com.michelet.user.application.port.RefreshTokenStore;
import java.time.Duration;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class RedisRefreshTokenStore implements RefreshTokenStore {
    @Override
    public void save(UUID userId, String refreshToken, Duration ttl) {

    }
}
