package com.example.possystem.api.mapper;

import com.example.possystem.api.model.import_product.ImportProduct;
import com.example.possystem.api.model.import_product.dto.CreateImportProductDto;
import com.example.possystem.api.model.import_product.dto.ImportProductDto;
import com.example.possystem.api.model.import_product.dto.UpdateImportProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImportProductMapper {

    // create
    @Mapping(source = "importUuid", target = "imports.uuid")
    @Mapping(source = "productUuid", target = "products.uuid")
    ImportProduct fromImportProductDto(CreateImportProductDto createImportProductDto);

    // update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateImportProductDto(@MappingTarget ImportProduct importProduct, UpdateImportProductDto updateImportProductDto);

    // get a
    @Mapping(source = "imports.staff", target = "importDto.staffDto")
    @Mapping(source = "imports.supplier", target = "importDto.supplierDto")
    @Mapping(source = "imports", target = "importDto")
    @Mapping(source = "products.category", target = "productDto.categoryDto")
    @Mapping(source = "products", target = "productDto")
    ImportProductDto toImportProductDto(ImportProduct importProduct);

    // get all
    List<ImportProductDto> ImportProductDtoList(List<ImportProduct> importProductList);
}
