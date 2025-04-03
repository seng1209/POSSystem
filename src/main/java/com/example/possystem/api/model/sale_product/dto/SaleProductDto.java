package com.example.possystem.api.model.sale_product.dto;

import com.example.possystem.api.model.product.dto.ProductDto;
import com.example.possystem.api.model.sale.dto.SaleDto;

import java.math.BigDecimal;

public record SaleProductDto(
    String uuid,
    SaleDto saleDto,
    ProductDto productDto,
    Integer quantity,
    BigDecimal amount,
    Boolean isDeleted
) {
}
