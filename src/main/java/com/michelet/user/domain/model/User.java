package com.michelet.user.domain.model;

import com.michelet.user.domain.enums.UserRole;
import com.michelet.user.domain.enums.UserStatus;
<<<<<<< HEAD
import com.michelet.user.domain.vo.*;
=======
import com.michelet.user.domain.vo.LoginId;
import com.michelet.user.domain.vo.Password;
import com.michelet.user.domain.vo.Phone;
import com.michelet.user.domain.vo.UserId;
>>>>>>> 0058de2 (feat: 유저 도메인 모델링)

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
