package com.michelet.user.application.dto.command;

public record LoginCommand(
    String loginId,
    String password
) {
}
