package com.example.possystem.api.model.sale_product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateSaleProductDto(
        @NotBlank(message = "Sale UUID is required.")
        String saleUuid,
        @NotBlank(message = "Product UUID is required.")
        String productUuid,
        @Positive(message = "Quantity must be Positive number and larger than 0.")
        Integer quantity,
        @Positive(message = "Amount must be Positive number and larger than 0.")
        BigDecimal amount
) {
}
