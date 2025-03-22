package com.example.possystem.base;

import lombok.Builder;

@Builder
public record MethodNotSupportError(
        String message,
        String code,
        String method,
        String status
) {
}
