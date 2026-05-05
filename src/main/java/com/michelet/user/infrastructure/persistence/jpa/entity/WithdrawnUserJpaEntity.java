package com.michelet.user.infrastructure.persistence.jpa.entity;

import com.michelet.user.domain.model.WithdrawnUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "withdrawn_user"
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WithdrawnUserJpaEntity {

    @Id
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", columnDefinition = "uuid", nullable = false)
    private UUID userId;

    @Column(name = "email_hash", length = 64, nullable = false)
    private String emailHash;

    @Column(name = "phone_hash", length = 64, nullable = false)
    private String phoneHash;

    @Column(name = "withdrawn_at", nullable = false)
    private LocalDateTime withdrawnAt;

    @Column(name = "rejoin_allowed_at", nullable = false)
    private LocalDateTime rejoinAllowedAt;

    @Column(name = "retained_until", nullable = false)
    private LocalDateTime retainedUntil;



    public static WithdrawnUserJpaEntity from(WithdrawnUser withdrawnUser){
        return new WithdrawnUserJpaEntity(
            withdrawnUser.getId(),
            withdrawnUser.getUserId().value(),
            withdrawnUser.getEmailHash(),
            withdrawnUser.getPhoneHash(),
            withdrawnUser.getWithdrawnAt(),
            withdrawnUser.getRejoinAllowedAt(),
            withdrawnUser.getRetentionUntil()
        );
    }
}
