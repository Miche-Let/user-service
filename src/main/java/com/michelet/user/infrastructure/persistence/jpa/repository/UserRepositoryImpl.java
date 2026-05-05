package com.michelet.user.infrastructure.persistence.jpa.repository;

import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;
import com.michelet.user.domain.model.User;
import com.michelet.user.domain.repository.UserRepository;
import com.michelet.user.infrastructure.persistence.jpa.entity.UserJpaEntity;
import com.michelet.user.infrastructure.persistence.jpa.entity.UserMapper;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public boolean existsByLoginIdOrEmailOrPhone(String loginId,String email, String phone) {
        return jpaRepository.existsByLoginIdOrEmailOrPhone(loginId, email, phone);
    }

    @Override
    public User save(User user) {
        try {
            UserJpaEntity savedEntity = jpaRepository.save(UserMapper.toJpaEntity(user));
            return UserMapper.toDomainEntity(savedEntity);
        } catch (DataIntegrityViolationException e) {
            throw new UserException(UserErrorCode.DUPLICATE_USER);
        }
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return jpaRepository.findByLoginId(loginId).map(UserMapper::toDomainEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(UserMapper::toDomainEntity);
    }

    @Override
    public void delete(User user) {
        jpaRepository.delete(UserMapper.toJpaEntity(user));
    }
}
