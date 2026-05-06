package com.michelet.user.infrastructure.feign.dto;

public record FeignApiResponse<T>(
    String code,
    String message,
    T data
) {
}
