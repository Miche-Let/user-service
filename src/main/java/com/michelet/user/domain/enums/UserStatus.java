package com.michelet.user.domain.enums;

public enum UserStatus {
    ACTIVE("활성화 상태"),
    DORMANT("휴면 상태"),
    SUSPENDED("제재 상태");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }
}
