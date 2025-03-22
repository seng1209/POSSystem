package com.example.possystem.api.mapper;

import com.example.possystem.api.model.imports.Import;
import com.example.possystem.api.model.imports.dto.CreateImportDto;
import com.example.possystem.api.model.imports.dto.ImportDto;
import com.example.possystem.api.model.imports.dto.UpdateImportDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImportMapper {

    @Mapping(source = "supplierUuid", target = "supplier.uuid")
    @Mapping(source = "staffUuid", target = "staff.uuid")
    Import formImportDto(CreateImportDto createImportDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void formUpdateImportDto(@MappingTarget Import imports, UpdateImportDto updateImportDto);

    @Mapping(source = "supplier", target = "supplierDto")
    @Mapping(source = "staff", target = "staffDto")
    ImportDto toImportDto(Import imports);

    List<ImportDto> toImportDtoList(List<Import> importList);

}
