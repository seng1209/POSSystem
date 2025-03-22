package com.example.possystem.api.model.category.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryDto(
        @NotBlank(message = "Category is required.")
        String categoryName
) {
}
