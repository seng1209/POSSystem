package com.example.possystem.api.controller;

import com.example.possystem.api.model.import_product.dto.CreateImportProductDto;
import com.example.possystem.api.model.import_product.dto.ImportProductDto;
import com.example.possystem.api.model.import_product.dto.UpdateImportProductDto;
import com.example.possystem.api.service.import_product.ImportProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/imports_products")
@RequiredArgsConstructor
public class ImportProductController {

    private final ImportProductService importProductService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ImportProductDto createImportProduct(@RequestBody @Valid CreateImportProductDto createImportProductDto){
        return importProductService.createImportProduct(createImportProductDto);
    }

    @PatchMapping("/{uuid}")
    public ImportProductDto updateByUuid(@PathVariable String uuid, @RequestBody @Valid UpdateImportProductDto updateImportProductDto){
        return importProductService.updateByUuid(uuid, updateImportProductDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void deleteByUuid(@PathVariable String uuid){
        importProductService.deleteByUuid(uuid);
    }

    @GetMapping
    public List<ImportProductDto> findAll(){
        return importProductService.findAll();
    }

    @GetMapping("/{uuid}")
    public ImportProductDto findByUuid(@PathVariable String uuid){
        return importProductService.findByUuid(uuid);
    }

    @GetMapping("/import/{importUuid}")
    public List<ImportProductDto> findAllByImportUuid(@PathVariable String importUuid){
        return importProductService.findAllByImportUuid(importUuid);
    }

}
