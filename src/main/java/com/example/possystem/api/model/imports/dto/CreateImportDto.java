package com.example.possystem.api.model.imports.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateImportDto(
        @NotBlank(message = "Supplier UUID is required.")
        String supplierUuid,
        @NotBlank(message = "Staff UUID is required.")
        String staffUuid
) {
}
