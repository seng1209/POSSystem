package com.example.possystem.api.model.sale.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateSaleDto(
        @NotBlank(message = "Staff UUID is required.")
        String staffUuid,
        @NotBlank(message = "Customer Phone is required.")
        String customerPhone
) {
}
