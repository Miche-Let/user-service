package com.michelet.user.infrastructure.persistence.jpa;

import com.michelet.user.domain.model.User;
import com.michelet.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public boolean existsByEmailOrPhone(String email, String phone) {
        return jpaRepository.existsByEmailOrPhone(email, phone);
    }

    @Override
    public User save(User user) {
        UserJpaEntity savedEntity = jpaRepository.save(UserMapper.toJpaEntity(user));
        return UserMapper.toDomainEntity(savedEntity);
    }
}
