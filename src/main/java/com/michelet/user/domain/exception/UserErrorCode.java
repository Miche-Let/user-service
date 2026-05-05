package com.michelet.user.domain.exception;

import com.michelet.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    INVALID_USER_ID(HttpStatus.BAD_REQUEST.value(), "UR_E001", "유저 아이디가 유효하지 않습니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST.value(), "UR_E002", "이메일이 유효하지 않습니다."),
    INVALID_LOGIN_ID(HttpStatus.BAD_REQUEST.value(), "UR_E003", "로그인 아이디가 유효하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST.value(), "UR_E004", "비밀번호가 유효하지 않습니다."),
    INVALID_PHONE_NUMBER(HttpStatus.BAD_REQUEST.value(), "UR_E005", "전화번호가 유효하지 않습니다."),
    INVALID_USER_ROLE(HttpStatus.BAD_REQUEST.value(),"UR_E006","유저 권한이 유효하지 않습니다."),
    DUPLICATE_USER(HttpStatus.CONFLICT.value(),"UR_E007","이미 가입된 유저 입니다."),
    INVALID_USER_NAME(HttpStatus.BAD_REQUEST.value(), "UR_E008","유저 이름이 유효하지 않습니다."),
    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST.value(), "UR_E009","계정 정보가 유효하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "UR_E010","해당 회원을 찾을 수 없습니다."),
    REJOIN_NOT_ALLOWED(HttpStatus.CONFLICT.value(), "UR_E011", "재가입이 제한된 사용자입니다. 잠시 후 다시 시도해주세요."),
    WITHDRAWAL_BLOCKED_BY_ACTIVE_RESERVATION(HttpStatus.CONFLICT.value(), "UR_E012", "진행 중인 예약이 있어 탈퇴할 수 없습니다. 예약을 모두 완료하거나 취소한 후 다시 시도해주세요."),
    RESERVATION_SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(), "UR_E013", "예약 서비스와 통신할 수 없습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED.value(), "UR_E014", "리프레시 토큰이 유효하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
