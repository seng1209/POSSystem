package com.example.possystem.api.mapper;

import com.example.possystem.api.model.staff.Staff;
import com.example.possystem.api.model.staff.dto.CreateStaffDto;
import com.example.possystem.api.model.staff.dto.StaffDto;
import com.example.possystem.api.model.staff.dto.UpdateStaffDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    Staff formStaffDto (CreateStaffDto createStaffDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void formUpdateStaffDto(@MappingTarget Staff staff, UpdateStaffDto updateStaffDto);

    StaffDto toStaffDto(Staff staff);

    List<StaffDto> toStaffDtoList(List<Staff> staffList);

}
