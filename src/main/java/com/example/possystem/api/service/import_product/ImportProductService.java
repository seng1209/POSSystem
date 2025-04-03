package com.example.possystem.api.service.import_product;

import com.example.possystem.api.model.import_product.dto.CreateImportProductDto;
import com.example.possystem.api.model.import_product.dto.ImportProductDto;
import com.example.possystem.api.model.import_product.dto.UpdateImportProductDto;

import java.util.List;

public interface ImportProductService {

    // create
    ImportProductDto createImportProduct(CreateImportProductDto createImportProductDto);

    // update by uuid
    ImportProductDto updateByUuid(String uuid, UpdateImportProductDto updateImportProductDto);

    // delete by uuid
    void deleteByUuid(String uuid);

    // get all
    List<ImportProductDto> findAll();

    // get by uuid
    ImportProductDto findByUuid(String uuid);

    // get all by import uuid
    List<ImportProductDto> findAllByImportUuid(String importUuid);

}
