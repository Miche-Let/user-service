package com.michelet.user.config;

import com.michelet.user.application.port.RefreshTokenStore;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class RedisConfig {
    @Bean
    @Primary
    public RefreshTokenStore refreshTokenStore() {
        return new RefreshTokenStore() {
            private final Map<UUID, String> store = new ConcurrentHashMap<>();

            @Override
            public void save(UUID userId, String refreshToken, Duration ttl) {
            }

            @Override
            public Optional<String> find(UUID userId) {
                return Optional.empty();
            }

            @Override
            public void delete(UUID userId) {
            }
        };
    }
}
