package com.michelet.user.infrastructure.persistence.jpa.repository;

import com.michelet.user.infrastructure.persistence.jpa.entity.WithdrawnUserJpaEntity;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WithdrawnUserJpaRepository extends JpaRepository<WithdrawnUserJpaEntity, UUID> {
    @Query("""
        SELECT COUNT(w) > 0 FROM WithdrawnUserJpaEntity w
        WHERE (w.emailHash = :emailHash OR w.phoneHash = :phoneHash)
          AND w.rejoinAllowedAt > :now
    """)
    boolean existsActiveBlock(
        @Param("emailHash") String emailHash,
        @Param("phoneHash") String phoneHash,
        @Param("now") LocalDateTime now
    );
}
