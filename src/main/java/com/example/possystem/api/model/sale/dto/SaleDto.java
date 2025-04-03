package com.example.possystem.api.model.sale.dto;

import com.example.possystem.api.model.customer.dto.CustomerDto;
import com.example.possystem.api.model.staff.dto.StaffDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record SaleDto(
        String uuid,
        LocalDate saleDate,
        StaffDto staffDto,
        CustomerDto customerDto,
        BigDecimal totalAmount,
        Boolean isDeleted
) {
}
