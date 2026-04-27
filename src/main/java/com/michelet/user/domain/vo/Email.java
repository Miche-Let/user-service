package com.michelet.user.domain.vo;

import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;

public record Email(
        String value
) {
    public Email{
        validate(value);
    }
    public static Email of(String value){
        return new Email(value);
    }
    private void validate(String value){
        if(value== null || value.isBlank())
            throw new UserException(UserErrorCode.INVALID_EMAIL);
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if(!value.matches(emailRegex)){
            throw new UserException(UserErrorCode.INVALID_EMAIL);
        }
    }
}
