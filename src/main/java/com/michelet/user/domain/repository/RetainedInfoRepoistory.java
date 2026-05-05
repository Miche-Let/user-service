package com.michelet.user.domain.repository;

import com.michelet.user.domain.model.RetainedUserInfo;

public interface RetainedInfoRepoistory {
    RetainedUserInfo save(RetainedUserInfo retainedUserInfo);
}
