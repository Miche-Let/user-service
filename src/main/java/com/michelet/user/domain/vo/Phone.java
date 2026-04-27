package com.michelet.user.domain.vo;

import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;

public record Phone(
        String value
) {
    public Phone{
        validate(value);
    }
    public static Phone of(String value){
        return new Phone(value);
    }
    private void validate(String value){
        if(value== null || value.isBlank())
            throw new UserException(UserErrorCode.INVALID_PHONE_NUMBER);
        String phoneRegex = "^010-\\d{4}-\\d{4}$";
        if(!value.matches(phoneRegex)){
            throw new UserException(UserErrorCode.INVALID_PHONE_NUMBER);
        }
    }
}
