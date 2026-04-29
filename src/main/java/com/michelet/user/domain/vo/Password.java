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
        validateRaw(value);
        return new Password(value);
    }
    public static Password fromEncoded(String value){
        validate(value);
        return new Password(value);
    }
    private static void validate(String value){
        if(value== null || value.isBlank())
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
    }
    private static void validateRaw(String value){
        validate(value);
        String passwordRegex =
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{8,20}$";
        if(!value.matches(passwordRegex)){
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }
    }
}
