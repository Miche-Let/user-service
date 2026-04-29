package com.michelet.user.application.dto.result;

public record LoginResult(
    String accessToken,
    String refreshToken
) {
}
