package com.michelet.user.domain.repository;

import com.michelet.user.domain.model.User;

public interface UserRepository {
    boolean existsByLoginIdOrEmailOrPhone(String loginId,String email, String phone);
    User save(User user);
}
