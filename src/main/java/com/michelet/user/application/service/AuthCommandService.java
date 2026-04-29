package com.michelet.user.application.service;

import com.michelet.user.application.dto.command.LoginCommand;
import com.michelet.user.application.dto.result.LoginResult;
import com.michelet.user.application.port.PasswordEncryptor;
import com.michelet.user.application.port.RefreshTokenStore;
import com.michelet.user.application.port.TokenProvider;
import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;
import com.michelet.user.domain.model.User;
import com.michelet.user.domain.repository.UserRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCommandService {
    private final PasswordEncryptor passwordEncryptor;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenStore refreshTokenStore;

    public LoginResult login(LoginCommand command) {
        User user = userRepository.findByLoginId(command.loginId())
            .orElseThrow(() -> new UserException(UserErrorCode.INVALID_CREDENTIALS));
        if (!passwordEncryptor.matches(command.password(), user.getPassword().value())) {
            throw new UserException(UserErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = tokenProvider.createAccessToken(user.getId().value(), user.getRole().name());
        String refreshToken = tokenProvider.createRefreshToken(user.getId().value());

        refreshTokenStore.save(
            user.getId().value(),
            refreshToken,
            Duration.ofSeconds( tokenProvider.getRefreshTokenExpirationSeconds()));

        return new LoginResult(accessToken, refreshToken);
    }
}
