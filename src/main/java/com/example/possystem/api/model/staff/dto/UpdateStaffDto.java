package com.example.possystem.api.model.staff.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record UpdateStaffDto(
        String image,
        String nameKh,
        String nameEng,
        String gender,
        LocalDate birthDate,
        String phone,
        @Email(message = "Email is not valid", regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        String email,
        String address,
        String position,
        @PositiveOrZero(message = "Salary must be 0 or Number.")
        BigDecimal salary
) {
}
