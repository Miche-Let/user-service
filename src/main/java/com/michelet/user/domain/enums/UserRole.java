package com.michelet.user.domain.enums;

import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;

public enum UserRole {
    USER("일반 사용자"),
    OWNER("가게 사장님"),
    MASTER("관리자");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }
}
