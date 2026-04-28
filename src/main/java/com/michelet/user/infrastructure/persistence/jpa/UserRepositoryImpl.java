package com.michelet.user.infrastructure.persistence.jpa;

import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;
import com.michelet.user.domain.model.User;
import com.michelet.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public boolean existsByLoginIdOrEmailOrPhone(String loginId,String email, String phone) {
        return jpaRepository.existsByEmailOrPhone(loginId, email, phone);
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
}
