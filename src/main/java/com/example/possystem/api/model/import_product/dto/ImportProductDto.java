package com.example.possystem.api.model.import_product.dto;

import com.example.possystem.api.model.imports.dto.ImportDto;
import com.example.possystem.api.model.product.dto.ProductDto;

import java.math.BigDecimal;

public record ImportProductDto(
        String uuid,
        ImportDto importDto,
        ProductDto productDto,
        Integer quantity,
        BigDecimal amount,
        Boolean isDeleted
) {
}
