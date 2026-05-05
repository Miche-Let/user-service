package com.michelet.user.domain.repository;

import com.michelet.user.domain.model.WithdrawnUser;
import java.time.LocalDateTime;

public interface WithdrawnUserRepository {
    WithdrawnUser save(WithdrawnUser withdrawnUser);
    boolean existsActiveBlock(String emailHash, String phoneHash, LocalDateTime now);
}
