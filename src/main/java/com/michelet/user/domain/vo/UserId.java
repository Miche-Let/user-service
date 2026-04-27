package com.michelet.user.domain.vo;

import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;

import java.util.UUID;

public record UserId(
        UUID value
) {
    public UserId {
        if (value == null)
            throw new UserException(UserErrorCode.INVALID_USER_ID);
    }

    public static UserId generate(){
        return new UserId(UUID.randomUUID());
    }
    public static UserId of(UUID value){
        return new UserId(value);
    }
}
