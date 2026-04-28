package com.michelet.user.application.dto.command;

import com.michelet.user.domain.enums.UserRole;

public record SignUpCommand(
        String loginId,
        String password,
        String name,
        String email,
        String phone,
        UserRole userRole
) {
}
