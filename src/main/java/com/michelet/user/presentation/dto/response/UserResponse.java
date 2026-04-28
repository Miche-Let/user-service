package com.michelet.user.presentation.dto.response;

import com.michelet.user.application.dto.result.UserResult;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID userId,
        String loginId,
        String name,
        String email,
        String phone,
        String role,
        String status,
        LocalDateTime lastLoginAt
) {
    public static UserResponse from(UserResult result){
        return new UserResponse(
                result.id(),
                result.loginId(),
                result.name(),
                result.email(),
                result.phone(),
                result.role().name(),
                result.status().name(),
                result.lastLoginAt()
        );
    }
}
