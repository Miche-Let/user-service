package com.michelet.user.domain.exception;

import com.michelet.common.exception.BusinessException;

public class UserException extends BusinessException {
    public UserException(UserErrorCode code) {
        super(code);
    }
}
