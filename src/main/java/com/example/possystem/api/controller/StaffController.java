package com.example.possystem.api.controller;

import com.example.possystem.api.model.staff.dto.CreateStaffDto;
import com.example.possystem.api.model.staff.dto.StaffDto;
import com.example.possystem.api.model.staff.dto.UpdateStaffDto;
import com.example.possystem.api.service.staff.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StaffDto createStaff(@RequestBody @Valid CreateStaffDto createStaffDto){
        return staffService.createStaff(createStaffDto);
    }

    @PatchMapping("/{uuid}")
    public StaffDto updateStaffByUuid(@PathVariable String uuid, @RequestBody @Valid UpdateStaffDto updateStaffDto){
        return staffService.updateStaffByUuid(uuid, updateStaffDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void deleteByUuid(@PathVariable String uuid){
        staffService.deleteByUuid(uuid);
    }

    @GetMapping("/uuid/{uuid}")
    public StaffDto findByUuid(@PathVariable String uuid){
        return staffService.findByUuid(uuid);
    }

    @GetMapping("/phone/{phone}")
    public StaffDto findByPhone(@PathVariable String phone){
        return staffService.findByPhone(phone);
    }

    @GetMapping
    public List<StaffDto> findAll(){
        return staffService.findAll();
    }

}
