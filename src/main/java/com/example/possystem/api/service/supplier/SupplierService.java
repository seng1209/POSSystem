package com.example.possystem.api.service.supplier;

import com.example.possystem.api.model.supplier.dto.CreateSupplierDto;
import com.example.possystem.api.model.supplier.dto.SupplierDto;
import com.example.possystem.api.model.supplier.dto.UpdateSupplierDto;

import java.util.List;

public interface SupplierService {

    SupplierDto createSupplier(CreateSupplierDto createSupplierDto);

    SupplierDto updateByUuid(String uuid, UpdateSupplierDto updateSupplierDto);

    void deleteSupplierByUuid(String uuid);

    SupplierDto findByUuid(String uuid);

    SupplierDto findByContactPhone(String contactPhone);

    List<SupplierDto> findAll();

}
