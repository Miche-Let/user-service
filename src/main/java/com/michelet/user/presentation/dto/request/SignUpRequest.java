package com.michelet.user.presentation.dto.request;

import com.michelet.user.application.dto.command.SignUpCommand;
import com.michelet.user.domain.enums.UserRole;
import jakarta.validation.constraints.*;

public record SignUpRequest(
        @NotBlank(message = "아이디를 입력해주세요.")
        @Pattern(
                regexp = "^[a-zA-Z0-9]{4,20}$",
                message = "아이디는 4 ~ 20자 사이의 대소문자와 숫자로 구성되어야 합니다."
        )
        String loginId,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{8,20}$",
                message = "비밀번호는 8~20자이며, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다."
        )
        String password,

        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(
                regexp = "^(01[016789])-?\\d{3,4}-?\\d{4}$",
                message = "올바른 휴대폰 번호 형식이 아닙니다."
        )
        String phone,

        @NotNull(message = "유저 권한을 입력해주세요.")
        UserRole userRole
) {
        public SignUpCommand toCommand(){
                return new SignUpCommand(
                        loginId,
                        password,
                        name,
                        email,
                        phone,
                        userRole
                );
        }
}
