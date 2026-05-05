package com.michelet.user.application.dto.result;

public record ReissueResult(
    String accessToken,
    String refreshToken
) {
}
