package com.example.possystem.api.model.supplier.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateSupplierDto(
        @NotBlank(message = "Supplier Name is required.")
        String supplierName,
        @NotBlank(message = "Contact Phone is required.")
        String contactPhone,
        @NotBlank(message = "Contact Address is required.")
        String contactAddress
) {
}
