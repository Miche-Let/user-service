package com.michelet.user.application.service;

import com.michelet.user.application.port.HashGenerator;
import com.michelet.user.application.port.PersonalInfoEncryptor;
import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;
import com.michelet.user.domain.model.RetainedUserInfo;
import com.michelet.user.domain.model.User;
import com.michelet.user.domain.model.WithdrawnUser;
import com.michelet.user.domain.repository.RetainedInfoRepoistory;
import com.michelet.user.domain.repository.UserRepository;
import com.michelet.user.domain.repository.WithdrawnUserRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserWithdrawalService {

    private final UserRepository userRepository;
    private final WithdrawnUserRepository withdrawnUserRepository;
    private final RetainedInfoRepoistory retainedInfoRepository;
    private final HashGenerator hashGenerator;
    private final PersonalInfoEncryptor personalInfoEncryptor;

    @Transactional
    public void withdraw(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        String emailHash = hashGenerator.hash(user.getEmail().value());
        String phoneHash = hashGenerator.hash(user.getPhone().value());
        LocalDateTime withdrawnAt = LocalDateTime.now();

        WithdrawnUser withdrawnUser = WithdrawnUser.create(
            user.getId(),
            emailHash,
            phoneHash,
            withdrawnAt
        );
        withdrawnUserRepository.save(withdrawnUser);

        RetainedUserInfo retainedUserInfo = RetainedUserInfo.create(
            user.getId(),
            personalInfoEncryptor.encrypt(user.getEmail().value()),
            personalInfoEncryptor.encrypt(user.getPhone().value()),
            personalInfoEncryptor.encrypt(user.getName()),
            withdrawnAt
        );
        retainedInfoRepository.save(retainedUserInfo);

        userRepository.delete(user);
    }
}
