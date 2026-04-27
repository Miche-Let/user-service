package com.michelet.user.domain.vo;

import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;

public record LoginId(
        String value
) {
    public LoginId{
        validate(value);
    }
    public static LoginId of(String value){
        return new LoginId(value);
    }
    private void validate(String value){
        if(value== null || value.isBlank())
            throw new UserException(UserErrorCode.INVALID_LOGIN_ID);
        String loginIdRegex = "^[a-zA-Z0-9]{4,20}$";
        if(!value.matches(loginIdRegex)){
            throw new UserException(UserErrorCode.INVALID_LOGIN_ID);
        }
    }
}
