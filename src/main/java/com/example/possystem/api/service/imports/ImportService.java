package com.example.possystem.api.service.imports;

import com.example.possystem.api.model.imports.dto.CreateImportDto;
import com.example.possystem.api.model.imports.dto.ImportDto;
import com.example.possystem.api.model.imports.dto.UpdateImportDto;

import java.time.LocalDate;
import java.util.List;

public interface ImportService {

    ImportDto createImport(CreateImportDto createImportDto);

    ImportDto updateImportByUuid(String uuid, UpdateImportDto updateImportDto);

    void deleteImportByUuid(String uuid);

    ImportDto findByUuid(String uuid);

    List<ImportDto> findAllByImportDate(LocalDate importDate);

    List<ImportDto> findAll();

}
