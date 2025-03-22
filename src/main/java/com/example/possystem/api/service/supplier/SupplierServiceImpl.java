package com.example.possystem.api.service.supplier;

import com.example.possystem.api.mapper.SupplierMapper;
import com.example.possystem.api.model.supplier.Supplier;
import com.example.possystem.api.model.supplier.dto.CreateSupplierDto;
import com.example.possystem.api.model.supplier.dto.SupplierDto;
import com.example.possystem.api.model.supplier.dto.UpdateSupplierDto;
import com.example.possystem.api.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierDto createSupplier(CreateSupplierDto createSupplierDto) {
        if (supplierRepository.existsByContactPhone(createSupplierDto.contactPhone())){
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Contact Phone already existed.");
        }

        Supplier supplier = supplierMapper.formSupplierDto(createSupplierDto);
        supplier.setUuid(UUID.randomUUID().toString());

        supplierRepository.save(supplier);

        return supplierMapper.toSupplierDto(supplier);
    }

    @Override
    public SupplierDto updateByUuid(String uuid, UpdateSupplierDto updateSupplierDto) {
        if (supplierRepository.existsByUuid(uuid)){
            Supplier supplier = supplierRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.")
            );

            supplierMapper.formUpdateSupplierDto(supplier, updateSupplierDto);

            supplierRepository.save(supplier);

            return supplierMapper.toSupplierDto(supplier);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.");
    }

    @Override
    public void deleteSupplierByUuid(String uuid) {
        Supplier supplier = supplierRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.")
        );
        supplierRepository.delete(supplier);
    }

    @Override
    public SupplierDto findByUuid(String uuid) {
        Supplier supplier = supplierRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.")
        );
        return supplierMapper.toSupplierDto(supplier);
    }

    @Override
    public SupplierDto findByContactPhone(String contactPhone) {
        Supplier supplier = supplierRepository.findByContactPhone(contactPhone).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.")
        );
        return supplierMapper.toSupplierDto(supplier);
    }

    @Override
    public List<SupplierDto> findAll() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierMapper.toSupplierDtoList(supplierList);
    }
}
