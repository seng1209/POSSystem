package com.example.possystem.api.model.supplier.dto;

public record UpdateSupplierDto(
        String supplierName,
        String contactPhone,
        String contactAddress
) {
}
