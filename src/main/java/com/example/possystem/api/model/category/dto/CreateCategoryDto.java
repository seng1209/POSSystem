package com.example.possystem.api.model.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDto(
        @NotBlank(message = "Category is required.")
        String categoryName
) {
}
