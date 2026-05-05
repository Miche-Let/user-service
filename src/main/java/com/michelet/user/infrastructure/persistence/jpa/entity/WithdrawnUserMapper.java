package com.michelet.user.infrastructure.persistence.jpa.entity;

import com.michelet.user.domain.model.WithdrawnUser;
import com.michelet.user.domain.vo.UserId;
import org.springframework.stereotype.Component;

@Component
public class WithdrawnUserMapper {

    public WithdrawnUserJpaEntity toEntity(WithdrawnUser domain) {
        return WithdrawnUserJpaEntity.from(domain);
    }

    public WithdrawnUser toDomain(WithdrawnUserJpaEntity entity) {
        return WithdrawnUser.reconstruct(
            entity.getId(),
            UserId.of(entity.getUserId()),
            entity.getEmailHash(),
            entity.getPhoneHash(),
            entity.getWithdrawnAt(),
            entity.getRejoinAllowedAt(),
            entity.getRetainedUntil()
        );
    }
}
