package com.michelet.user.infrastructure.persistence.jpa;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    boolean existsByLoginIdOrEmailOrPhone(String loginId, String email, String phone);
}
