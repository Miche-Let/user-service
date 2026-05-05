package com.michelet.user.domain.model;

import com.michelet.user.domain.vo.UserId;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RetainedUserInfo {
    private UUID id;
    private UserId userId;
    private String encryptedName;
    private String encryptedEmail;
    private String encryptedPhone;
    private LocalDateTime withdrawnAt;
    private LocalDateTime retainedUntil;

    public static  RetainedUserInfo create(
        UserId userId,
        String encryptedEmail,
        String encryptedPhone,
        String encryptedName,
        LocalDateTime withdrawnAt
    ){
        return new RetainedUserInfo(
            UUID.randomUUID(),
            userId,
            encryptedName,
            encryptedEmail,
            encryptedPhone,
            withdrawnAt,
            withdrawnAt.plusYears(3)
        );
    }

    public static RetainedUserInfo reconstruct(
        UUID id, UserId userId,
        String encryptedName, String encryptedPhone, String encryptedEmail,
            LocalDateTime withdrawnAt, LocalDateTime retainedUntil
    ) {
        return new RetainedUserInfo(
            id, userId, encryptedName, encryptedEmail, encryptedPhone,
             withdrawnAt, retainedUntil
        );
    }

}
