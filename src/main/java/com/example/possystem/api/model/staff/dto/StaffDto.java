package com.example.possystem.api.model.staff.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record StaffDto(
        String uuid,
        String image,
        String nameKh,
        String nameEng,
        String gender,
        LocalDate birthDate,
        String phone,
        String email,
        String address,
        String position,
        BigDecimal salary,
        LocalDate hiredDate,
        Boolean isDeleted
) {
}
