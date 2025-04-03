package com.example.possystem.api.mapper;

import com.example.possystem.api.model.sale_product.SaleProduct;
import com.example.possystem.api.model.sale_product.dto.CreateSaleProductDto;
import com.example.possystem.api.model.sale_product.dto.SaleProductDto;
import com.example.possystem.api.model.sale_product.dto.UpdateSaleProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleProductMapper {

    // create
    @Mapping(source = "saleUuid", target = "sales.uuid")
    @Mapping(source = "productUuid", target = "products.uuid")
    SaleProduct fromSaleProductDto(CreateSaleProductDto createSaleProductDto);

    // update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateSaleProductDto(@MappingTarget SaleProduct saleProduct, UpdateSaleProductDto updateSaleProductDto);

    // get a
    @Mapping(source = "sales.staff", target = "saleDto.staffDto")
    @Mapping(source = "sales.customer", target = "saleDto.customerDto")
    @Mapping(source = "sales", target = "saleDto")
    @Mapping(source = "products.category", target = "productDto.categoryDto")
    @Mapping(source = "products", target = "productDto")
    SaleProductDto toSaleProductDto(SaleProduct saleProduct);

    List<SaleProductDto> toSaleProductDtoList(List<SaleProduct> saleProductList);

}
