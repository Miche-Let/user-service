package com.michelet.user.domain.vo;

import com.michelet.user.domain.exception.UserErrorCode;
import com.michelet.user.domain.exception.UserException;

public record Password(
        String value
) {
    public Password{
        validate(value);
    }
    public static Password of(String value){
        return new Password(value);
    }
    private void validate(String value){
        if(value== null || value.isBlank())
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        String passwordRegex =
                "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{8,20}$";
        if(!value.matches(passwordRegex)){
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }
    }
}
