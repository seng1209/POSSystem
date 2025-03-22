package com.example.possystem.api.service.staff;

import com.example.possystem.api.model.staff.Staff;
import com.example.possystem.api.model.staff.dto.CreateStaffDto;
import com.example.possystem.api.model.staff.dto.StaffDto;
import com.example.possystem.api.model.staff.dto.UpdateStaffDto;

import java.util.List;

public interface StaffService {

    StaffDto createStaff(CreateStaffDto createStaffDto);

    StaffDto updateStaffByUuid(String uuid, UpdateStaffDto updateStaffDto);

    void deleteByUuid(String uuid);

    StaffDto findByUuid(String uuid);

    StaffDto findByPhone(String phone);

    List<StaffDto> findAll();

}
