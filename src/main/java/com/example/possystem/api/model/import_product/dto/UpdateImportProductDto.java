package com.example.possystem.api.model.import_product.dto;

import java.math.BigDecimal;

public record UpdateImportProductDto(
        String importUuid,
        String productUuid,
        Integer quantity,
        BigDecimal amount
) {
}
