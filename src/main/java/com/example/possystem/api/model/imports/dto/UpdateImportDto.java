package com.example.possystem.api.model.imports.dto;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateImportDto(
        String supplierUuid,
        String staffUuid,
        @PositiveOrZero(message = "Total Amount is number it must be Positive number or Zero.")
        BigDecimal totalAmount
) {
}
