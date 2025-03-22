package com.example.possystem.api.model.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerDto(
        String nameKh,
        String nameEng,
        @NotBlank(message = "Phone is required.")
        String phone,
        @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @NotBlank(message = "Email is required.")
        String email,
        @NotBlank(message = "Customer Type is required.")
        String customerType,
        String address
) {
}
