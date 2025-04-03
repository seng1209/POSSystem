package com.example.possystem.api.mapper;

import com.example.possystem.api.model.sale.Sale;
import com.example.possystem.api.model.sale.dto.CreateSaleDto;
import com.example.possystem.api.model.sale.dto.SaleDto;
import com.example.possystem.api.model.sale.dto.UpdateSaleDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    // create
    @Mapping(source = "staffUuid", target = "staff.uuid")
    @Mapping(source = "customerPhone", target = "customer.phone")
    Sale formSaleDto(CreateSaleDto createSaleDto);

    // update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateSaleDto(@MappingTarget  Sale sale, UpdateSaleDto updateSaleDto);

    // get a
    @Mapping(source = "staff", target = "staffDto")
    @Mapping(source = "customer", target = "customerDto")
    SaleDto toSaleDto(Sale sale);

    List<SaleDto> toSaleDtoList(List<Sale> sales);

}
