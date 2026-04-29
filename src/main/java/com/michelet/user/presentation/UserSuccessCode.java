package com.michelet.user.presentation;

import com.michelet.common.response.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements SuccessCode {
    USER_CREATED(HttpStatus.CREATED, "US_S001", "회원가입에 성공하였습니다."),
    LOGIN_SUCCEED(HttpStatus.OK, "US_S002", "로그인에 성공하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
