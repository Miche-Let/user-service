package com.michelet.user.infrastructure.redis;

import com.michelet.user.application.port.RefreshTokenStore;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisRefreshTokenStore implements RefreshTokenStore {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void save(UUID userId, String refreshToken, Duration ttl) {
        stringRedisTemplate.opsForValue().set(key(userId),refreshToken,ttl);
    }

    private String key(UUID userId){
        return "refresh:"+userId;
    }
}
