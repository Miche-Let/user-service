package com.michelet.user.infrastructure.persistence.jpa.repository;

import com.michelet.user.domain.model.RetainedUserInfo;
import com.michelet.user.domain.repository.RetainedInfoRepoistory;
import com.michelet.user.infrastructure.persistence.jpa.entity.RetainedUserInfoJpaEntity;
import com.michelet.user.infrastructure.persistence.jpa.entity.RetainedUserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RetainedUserInfoRepositoryImpl implements RetainedInfoRepoistory {

    private final RetainedUserInfoJpaRepository jpaRepository;
    private final RetainedUserInfoMapper mapper;

    @Override
    public RetainedUserInfo save(RetainedUserInfo retainedUserInfo) {
        RetainedUserInfoJpaEntity saved = jpaRepository.save(mapper.toEntity(retainedUserInfo));
        return mapper.toDomain(saved);
    }
}
