package com.michelet.user.domain.repository;

import com.michelet.user.domain.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    boolean existsByLoginIdOrEmailOrPhone(String loginId,String email, String phone);
    User save(User user);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findById(UUID id);
    void delete(User user);
}
