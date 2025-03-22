package com.example.possystem.api.model.staff.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record CreateStaffDto(
        @NotBlank(message = "Image is required.")
        String image,
        @NotBlank(message = "Name Khmer is required.")
        String nameKh,
        @NotBlank(message = "Name English is required.")
        String nameEng,
        @NotBlank(message = "Gender is required.")
        String gender,
        @NotNull(message = "Birth Date is required.")
        LocalDate birthDate,
        @NotBlank(message = "Phone is required.")
        String phone,
        @Email(message = "Email is not valid", regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @NotEmpty(message = "Email cannot be empty")
        String email,
        @NotBlank(message = "Address is required.")
        String address,
        @NotBlank(message = "Position is required.")
        String position,
        @NotNull(message = "Salary is required.")
        @PositiveOrZero(message = "Salary must be 0 or Number.")
        BigDecimal salary
) {
}
