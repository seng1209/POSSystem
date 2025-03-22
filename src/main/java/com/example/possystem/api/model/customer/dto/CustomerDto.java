package com.example.possystem.api.model.customer.dto;

import lombok.Builder;

@Builder
public record CustomerDto(
        String uuid,
        String nameKh,
        String nameEng,
        String phone,
        String email,
        String address,
        String customerType) {
}
