package com.example.possystem.api.mapper;

import com.example.possystem.api.model.product.Product;
import com.example.possystem.api.model.product.dto.CreateProductDto;
import com.example.possystem.api.model.product.dto.ProductDto;
import com.example.possystem.api.model.product.dto.UpdateProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryUuid", target = "category.uuid")
    Product fromProductDto(CreateProductDto createProductDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void formUpdateProductDto(@MappingTarget Product product, UpdateProductDto updateProductDto);

    @Mapping(source = "category", target = "categoryDto")
    ProductDto toProductDto (Product product);

    List<ProductDto> toProductDtoList(List<Product> productList);

}
