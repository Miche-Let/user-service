package com.michelet.user.domain.exception;

import com.michelet.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    INVALID_USER_ID(400, "UR_E001", "유저 아이디가 유효하지 않습니다."),
    INVALID_EMAIL(400, "UR_E002", "이메일이 유효하지 않습니다."),
    INVALID_LOGIN_ID(400, "UR_E003", "로그인 아이디가 유효하지 않습니다."),
    INVALID_PASSWORD(400, "UR_E004", "비밀번호가 유효하지 않습니다."),
    INVALID_PHONE_NUMBER(400, "UR_E005", "전화번호가 유효하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
