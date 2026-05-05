package com.michelet.user.infrastructure.persistence.jpa.entity;

import com.michelet.user.domain.model.RetainedUserInfo;
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
    name = "retained_user_info"
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RetainedUserInfoJpaEntity {

    @Id
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", columnDefinition = "uuid", nullable = false)
    private UUID userId;

    @Column(name = "encrypted_name", nullable = false, columnDefinition = "TEXT")
    private String encryptedName;

    @Column(name = "encrypted_email", nullable = false, columnDefinition = "TEXT")
    private String encryptedEmail;

    @Column(name = "encrypted_phone", nullable = false, columnDefinition = "TEXT")
    private String encryptedPhone;

    @Column(name = "withdrawn_at", nullable = false)
    private LocalDateTime withdrawnAt;

    @Column(name = "retained_until", nullable = false)
    private LocalDateTime retainedUntil;

    public static RetainedUserInfoJpaEntity from(RetainedUserInfo domain){
        return new RetainedUserInfoJpaEntity(
            domain.getId(),
            domain.getUserId().value(),
            domain.getEncryptedName(),
            domain.getEncryptedEmail(),
            domain.getEncryptedPhone(),
            domain.getWithdrawnAt(),
            domain.getRetainedUntil()
        );
    }
}
