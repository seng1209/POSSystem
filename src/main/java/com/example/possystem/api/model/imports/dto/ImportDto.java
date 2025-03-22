package com.example.possystem.api.model.imports.dto;

import com.example.possystem.api.model.staff.dto.StaffDto;
import com.example.possystem.api.model.supplier.dto.SupplierDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ImportDto(
        String uuid,
        LocalDate importDate,
        SupplierDto supplierDto,
        StaffDto staffDto,
        BigDecimal totalAmount,
        Boolean isDeleted) {
}
