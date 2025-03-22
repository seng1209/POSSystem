package com.example.possystem.api.model.customer.dto;

public record UpdateCustomerDto(
        String nameKh,
        String nameEng,
        String phone,
        String email,
        String customerType,
        String address
) {
}
