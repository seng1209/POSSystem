package com.example.possystem.api.model.product.dto;

import com.example.possystem.api.model.category.Category;
import com.example.possystem.api.model.category.dto.CategoryDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDto(
        String uuid,
        String image,
        String name,
        BigDecimal price,
        String description,
//        Category category,
        CategoryDto categoryDto,
        Boolean inStock,
        Boolean isDeleted) {
}
