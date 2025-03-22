package com.example.possystem.api.model.supplier.dto;

import lombok.Builder;

@Builder
public record SupplierDto(
        String uuid,
        String supplierName,
        String contactPhone,
        String contactAddress) {
}
