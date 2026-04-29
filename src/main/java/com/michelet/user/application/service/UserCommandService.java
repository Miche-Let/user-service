package com.michelet.user.application.service;

import com.michelet.user.application.dto.command.SignUpCommand;
import com.michelet.user.application.dto.result.UserResult;
import com.michelet.user.application.port.PasswordEncryptor;
import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;
import com.michelet.user.domain.model.User;
import com.michelet.user.domain.repository.UserRepository;
import com.michelet.user.domain.vo.Email;
import com.michelet.user.domain.vo.LoginId;
import com.michelet.user.domain.vo.Password;
import com.michelet.user.domain.vo.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    @Transactional
    public UserResult signUp(SignUpCommand command){
        if(userRepository.existsByLoginIdOrEmailOrPhone(command.loginId(),command.email(), command.phone())){
            throw new UserException(UserErrorCode.DUPLICATE_USER);
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
}
