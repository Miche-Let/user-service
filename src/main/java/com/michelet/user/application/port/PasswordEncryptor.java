package com.michelet.user.application.port;

public interface PasswordEncryptor {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
