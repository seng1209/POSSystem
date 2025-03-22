package com.example.possystem.api.model.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateProductDto(
        @NotBlank(message = "Image is required!")
        String image,
        @NotBlank(message = "Name is required!")
        String name,
        @NotNull(message = "Price is required.")
        @PositiveOrZero(message = "Price must be integer.")
        BigDecimal price,
        String description,
        @NotBlank(message = "Category is required!")
        String categoryUuid
) {
}
