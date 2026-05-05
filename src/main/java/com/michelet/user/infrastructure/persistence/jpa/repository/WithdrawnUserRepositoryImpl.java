package com.michelet.user.infrastructure.persistence.jpa.repository;

import com.michelet.user.domain.model.WithdrawnUser;
import com.michelet.user.domain.repository.WithdrawnUserRepository;
import com.michelet.user.infrastructure.persistence.jpa.entity.WithdrawnUserJpaEntity;
import com.michelet.user.infrastructure.persistence.jpa.entity.WithdrawnUserMapper;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WithdrawnUserRepositoryImpl implements WithdrawnUserRepository {
    private final WithdrawnUserJpaRepository jpaRepository;
    private final WithdrawnUserMapper mapper;

    @Override
    public WithdrawnUser save(WithdrawnUser withdrawnUser) {
        WithdrawnUserJpaEntity saved = jpaRepository.save(mapper.toEntity(withdrawnUser));
        return mapper.toDomain(saved);
    }

    @Override
    public boolean existsActiveBlock(String emailHash, String phoneHash, LocalDateTime now) {
        return jpaRepository.existsActiveBlock(emailHash, phoneHash, now);
    }
}
