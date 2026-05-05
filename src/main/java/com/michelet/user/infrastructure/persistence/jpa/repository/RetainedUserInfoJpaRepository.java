package com.michelet.user.infrastructure.persistence.jpa.repository;

import com.michelet.user.infrastructure.persistence.jpa.entity.RetainedUserInfoJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetainedUserInfoJpaRepository extends JpaRepository<RetainedUserInfoJpaEntity, UUID> {
}
