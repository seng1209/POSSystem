package com.example.possystem.api.mapper;

import com.example.possystem.api.model.supplier.Supplier;
import com.example.possystem.api.model.supplier.dto.CreateSupplierDto;
import com.example.possystem.api.model.supplier.dto.SupplierDto;
import com.example.possystem.api.model.supplier.dto.UpdateSupplierDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier formSupplierDto(CreateSupplierDto createSupplierDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void formUpdateSupplierDto(@MappingTarget Supplier supplier, UpdateSupplierDto updateSupplierDto);

    SupplierDto toSupplierDto(Supplier supplier);

    List<SupplierDto> toSupplierDtoList(List<Supplier> supplierList);

}
