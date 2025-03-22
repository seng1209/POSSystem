package com.example.possystem.api.model.product.dto;

import com.example.possystem.api.model.category.dto.CategoryDto;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateProductDto(
        String image,
        String name,
        @PositiveOrZero(message = "Price must be Integer number.")
        BigDecimal price,
        String description,
        String categoryUuid,
        Boolean inStock,
        Boolean isDeleted
) {
}
