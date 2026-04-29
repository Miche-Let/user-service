package com.michelet.user.presentation.external;

import com.michelet.common.response.ApiResponse;
import com.michelet.user.application.dto.result.LoginResult;
import com.michelet.user.application.service.AuthCommandService;
import com.michelet.user.presentation.UserSuccessCode;
import com.michelet.user.presentation.dto.request.LoginRequest;
import com.michelet.user.presentation.dto.response.LoginResponse;
import com.michelet.user.presentation.support.TokenCookieProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
@Controller
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthCommandService authCommandService;
    private final TokenCookieProvider tokenCookieProvider;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResult result = authCommandService.login(request.toCommand());

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, tokenCookieProvider.create(result.refreshToken()).toString())
            .body(ApiResponse.ok(UserSuccessCode.LOGIN_SUCCEED, new LoginResponse(result.accessToken())));
    }
}
