package com.michelet.user.infrastructure.persistence.jpa;

import com.michelet.user.domain.model.User;

public class UserMapper {
    public static UserJpaEntity toJpaEntity(User user){
        return UserJpaEntity.from(user);
    }

    public static User toDomainEntity(UserJpaEntity jpaEntity){
        return User.reconstitute(
            jpaEntity.getId(),
                jpaEntity.getLoginId(),
                jpaEntity.getPassword(),
                jpaEntity.getName(),
                jpaEntity.getEmail(),
                jpaEntity.getPhone(),
                jpaEntity.getRole(),
                jpaEntity.getStatus(),
                jpaEntity.getLastLoginAt()
        );
    }
}
