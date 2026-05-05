package com.michelet.user.domain.model;

import com.michelet.user.domain.vo.UserId;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WithdrawnUser {
    private UUID id;
    private UserId userId;
    private String emailHash;
    private String phoneHash;
    private LocalDateTime withdrawnAt;
    private LocalDateTime rejoinAllowedAt;
    private LocalDateTime retentionUntil;

    public static WithdrawnUser create(
        UserId userId,
        String emailHash,
        String phoneHash,
        LocalDateTime withDrawnAt
    ){
        return new WithdrawnUser(
            UUID.randomUUID(),
            userId,
            emailHash,
            phoneHash,
            withDrawnAt,
            withDrawnAt.plusDays(1),
            withDrawnAt.plusDays(365)
        );
    }


    public static WithdrawnUser reconstruct(
        UUID id, UserId userId, String emailHash, String phoneHash,
        LocalDateTime withdrawnAt, LocalDateTime rejoinAllowedAt, LocalDateTime retainedUntil
    ) {
        return new WithdrawnUser(id, userId, emailHash, phoneHash, withdrawnAt, rejoinAllowedAt, retainedUntil);
    }
}
