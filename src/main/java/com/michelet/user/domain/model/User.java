package com.michelet.user.domain.model;

import com.michelet.user.domain.enums.UserRole;
import com.michelet.user.domain.enums.UserStatus;
import com.michelet.user.domain.vo.*;

import java.time.LocalDateTime;

public class User {
    private UserId id;
    private LoginId loginId;
    private Password password;
    private String name;
    private Email email;
    private Phone phone;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime lastLoginAt;
}
