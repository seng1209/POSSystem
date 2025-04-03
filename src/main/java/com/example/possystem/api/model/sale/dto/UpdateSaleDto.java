package com.example.possystem.api.model.sale.dto;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateSaleDto(
        String staffUuid,
        String customerPhone,
        @PositiveOrZero(message = "Total Amount is number it must be Positive number or Zero.")
        BigDecimal totalAmount
) {
}
