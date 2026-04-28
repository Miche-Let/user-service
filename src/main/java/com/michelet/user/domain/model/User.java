package com.michelet.user.domain.model;

import com.michelet.user.domain.enums.UserRole;
import com.michelet.user.domain.enums.UserStatus;
import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;
import com.michelet.user.domain.vo.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
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

    private User(
            LoginId loginId,
            Password password,
            String name,
            Email email,
            Phone phone,
            UserRole role
    ) {
        validate(loginId, password, name, email, phone, role);
        this.id = UserId.generate();
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = UserStatus.ACTIVE;
        this.lastLoginAt = null;
    }

    public static User create(LoginId loginId, Password password, String name, Email email, Phone phone, UserRole role){
        return new User(
                loginId,
                password,
                name,
                email,
                phone,
                role
        );
    }
    public static User reconstitute(
            UUID userId,
            String loginId,
            String password,
            String name,
            String email,
            String phone,
            UserRole role,
            UserStatus status,
            LocalDateTime lastLoginAt
    ){
        return new User(
                UserId.of(userId),
                LoginId.of(loginId),
                Password.of(password),
                name,
                Email.of(email),
                Phone.of(phone),
                role,
                status,
                lastLoginAt
        );
    }
    private void validate(LoginId loginId,
                          Password password,
                          String name,
                          Email email,
                          Phone phone,
                          UserRole role){
        if(loginId== null) throw new UserException(UserErrorCode.INVALID_LOGIN_ID);
        if (password == null) throw new UserException(UserErrorCode.INVALID_PASSWORD);
        if (email == null) throw new UserException(UserErrorCode.INVALID_EMAIL);
        if (phone == null) throw new UserException(UserErrorCode.INVALID_PHONE_NUMBER);
        if (role == null) throw new UserException(UserErrorCode.INVALID_USER_ROLE);
        if (name == null || name.isBlank()) throw new UserException(UserErrorCode.INVALID_USER_NAME);
    }
}
