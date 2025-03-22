package com.example.possystem.base;

import lombok.Builder;

@Builder
public record BaseError<T>(
        String message,
        Integer code,
        Boolean status,
        T errors
) {
}
