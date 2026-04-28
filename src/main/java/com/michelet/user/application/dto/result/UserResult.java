package com.michelet.user.application.dto.result;

import com.michelet.user.domain.enums.UserRole;
import com.michelet.user.domain.enums.UserStatus;
import com.michelet.user.domain.model.User;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResult(
        UUID id,
        String loginId,
        String name,
        String email,
        String phone,
        UserRole role,
        UserStatus status,
        LocalDateTime lastLoginAt
) {
    public static UserResult from(User user){
        return new UserResult(
                user.getId().value(),
                user.getLoginId().value(),
                user.getPassword().value(),
                user.getName(),
                user.getEmail().value(),
                user.getRole(),
                user.getStatus(),
                user.getLastLoginAt()
        );
    }
}
