package com.example.possystem.api.controller;

import com.example.possystem.api.model.supplier.dto.CreateSupplierDto;
import com.example.possystem.api.model.supplier.dto.SupplierDto;
import com.example.possystem.api.model.supplier.dto.UpdateSupplierDto;
import com.example.possystem.api.service.supplier.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SupplierDto createSupplier(@RequestBody @Valid CreateSupplierDto createSupplierDto){
        return supplierService.createSupplier(createSupplierDto);
    }

    @PutMapping("/{uuid}")
    public SupplierDto updateByUuid(@PathVariable String uuid, @RequestBody @Valid UpdateSupplierDto updateSupplierDto){
        return supplierService.updateByUuid(uuid, updateSupplierDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteSupplierByUuid(@PathVariable String uuid){
        supplierService.deleteSupplierByUuid(uuid);
    }

    @GetMapping("/uuid/{uuid}")
    public SupplierDto findByUuid(@PathVariable String uuid){
        return supplierService.findByUuid(uuid);
    }

    @GetMapping("/phone/{phone}")
    public SupplierDto findByContactPhone(@PathVariable String phone){
        return supplierService.findByContactPhone(phone);
    }

    @GetMapping
    public List<SupplierDto> findAll(){
        return supplierService.findAll();
    }

}
