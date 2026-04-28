package com.michelet.user.infrastructure.persistence.jpa;

import com.michelet.common.entity.BaseEntity;
import com.michelet.user.domain.enums.UserRole;
import com.michelet.user.domain.enums.UserStatus;
import com.michelet.user.domain.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJpaEntity extends BaseEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String loginId;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column
    private LocalDateTime lastLoginAt;

    public static UserJpaEntity from(User user){
        return new UserJpaEntity(
                user.getId().value(),
                user.getLoginId().value(),
                user.getPassword().value(),
                user.getName(),
                user.getEmail().value(),
                user.getPhone().value(),
                user.getRole(),
                user.getStatus(),
                user.getLastLoginAt()
        );
    }
}
