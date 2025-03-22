package com.example.possystem.base;

import lombok.Builder;

@Builder
public record FieldError(
        String field,
        String message
) {
}
