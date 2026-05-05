package com.michelet.user.infrastructure.persistence.jpa.entity;

import com.michelet.user.domain.model.RetainedUserInfo;
import com.michelet.user.domain.vo.UserId;
import org.springframework.stereotype.Component;

@Component
public class RetainedUserInfoMapper {

    public RetainedUserInfoJpaEntity toEntity(RetainedUserInfo domain) {
        return RetainedUserInfoJpaEntity.from(domain);
    }

    public RetainedUserInfo toDomain(RetainedUserInfoJpaEntity entity) {
        return RetainedUserInfo.reconstruct(
            entity.getId(),
            UserId.of(entity.getUserId()),
            entity.getEncryptedName(),
            entity.getEncryptedPhone(),
            entity.getEncryptedEmail(),
            entity.getWithdrawnAt(),
            entity.getRetainedUntil()
        );
    }
}
