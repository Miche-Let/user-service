package com.michelet.user.application.service;

import com.michelet.user.application.dto.command.SignUpCommand;
import com.michelet.user.application.dto.result.UserResult;
import com.michelet.user.application.port.HashGenerator;
import com.michelet.user.application.port.PasswordEncryptor;
import com.michelet.user.application.port.ReservationPort;
import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;
import com.michelet.user.domain.model.User;
import com.michelet.user.domain.repository.UserRepository;
import com.michelet.user.domain.repository.WithdrawnUserRepository;
import com.michelet.user.domain.vo.Email;
import com.michelet.user.domain.vo.LoginId;
import com.michelet.user.domain.vo.Password;
import com.michelet.user.domain.vo.Phone;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;
    private final HashGenerator hashGenerator;
    private final WithdrawnUserRepository withdrawnUserRepository;
    private final UserWithdrawalService withdrawInternal;
    private final ReservationPort reservationPort;

    @Transactional
    public UserResult signUp(SignUpCommand command){
        if(userRepository.existsByLoginIdOrEmailOrPhone(command.loginId(),command.email(), command.phone())){
            throw new UserException(UserErrorCode.DUPLICATE_USER);
        }

        String emailHash = hashGenerator.hash(command.email());
        String phoneHash = hashGenerator.hash(command.phone());
        if (withdrawnUserRepository.existsActiveBlock(emailHash, phoneHash, LocalDateTime.now())) {
            throw new UserException(UserErrorCode.REJOIN_NOT_ALLOWED);
        }

        Password password = Password.of(command.password());
        User user = User.create(
                LoginId.of(command.loginId()),
                Password.fromEncoded(passwordEncryptor.encode(password.value())),
                command.name(),
                Email.of(command.email()),
                Phone.of(command.phone()),
                command.userRole()
        );
        User savedUser = userRepository.save(user);

        return UserResult.from(savedUser);
    }

    public void withdraw(UUID userId) {
        if (reservationPort.hasActiveReservation(userId)) {
            throw new UserException(UserErrorCode.WITHDRAWAL_BLOCKED_BY_ACTIVE_RESERVATION);
        }
        withdrawInternal.withdraw(userId);
    }
}
