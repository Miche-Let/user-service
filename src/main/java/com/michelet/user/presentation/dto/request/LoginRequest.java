package com.michelet.user.presentation.dto.request;

import com.michelet.user.application.dto.command.LoginCommand;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "아이디를 입력해주세요.")
    String loginId,

    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password
) {
    public LoginCommand toCommand(){
        return new LoginCommand(loginId,password);
    }
}
