package com.example.possystem.api.service.staff;

import com.example.possystem.api.mapper.StaffMapper;
import com.example.possystem.api.model.staff.Staff;
import com.example.possystem.api.model.staff.dto.CreateStaffDto;
import com.example.possystem.api.model.staff.dto.StaffDto;
import com.example.possystem.api.model.staff.dto.UpdateStaffDto;
import com.example.possystem.api.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Override
    public StaffDto createStaff(CreateStaffDto createStaffDto) {

        if (staffRepository.existsByPhone(createStaffDto.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone already existed.");
        }

        if (staffRepository.existsByEmail(createStaffDto.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already existed.");
        }

        Staff staff = staffMapper.formStaffDto(createStaffDto);
        staff.setUuid(UUID.randomUUID().toString());
        staff.setHiredDate(LocalDate.now());
        staff.setIsDeleted(false);

        staffRepository.save(staff);

        return staffMapper.toStaffDto(staff);
    }

    @Override
    public StaffDto updateStaffByUuid(String uuid, UpdateStaffDto updateStaffDto) {
        if (staffRepository.existsByUuid(uuid)){
            Staff staff = staffRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.")
            );

            staffMapper.formUpdateStaffDto(staff, updateStaffDto);

            staffRepository.save(staff);

            return staffMapper.toStaffDto(staff);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.");
    }

    @Transactional
    @Override
    public void deleteByUuid(String uuid) {
        if (staffRepository.existsByUuid(uuid)){
            staffRepository.deleteByUuid(uuid);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.");
    }

    @Override
    public StaffDto findByUuid(String uuid) {
        Staff staff = staffRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.")
        );
        return staffMapper.toStaffDto(staff);
    }

    @Override
    public StaffDto findByPhone(String phone) {
        Staff staff = staffRepository.findByPhone(phone).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found.")
        );
        return staffMapper.toStaffDto(staff);
    }

    @Override
    public List<StaffDto> findAll() {
        List<Staff> staffList = staffRepository.findAllByIsDeletedIsFalse();
        return staffMapper.toStaffDtoList(staffList);
    }
}
