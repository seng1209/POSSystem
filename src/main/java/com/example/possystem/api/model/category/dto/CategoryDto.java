package com.example.possystem.api.model.category.dto;

import lombok.Builder;

@Builder
public record CategoryDto(
        String uuid,
        String categoryName) {
}
