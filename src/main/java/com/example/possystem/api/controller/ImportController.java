package com.example.possystem.api.controller;

import com.example.possystem.api.model.imports.dto.CreateImportDto;
import com.example.possystem.api.model.imports.dto.ImportDto;
import com.example.possystem.api.model.imports.dto.UpdateImportDto;
import com.example.possystem.api.service.imports.ImportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/imports")
@RequiredArgsConstructor
public class ImportController {

    private final ImportService importService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ImportDto createImport(@RequestBody @Valid CreateImportDto createImportDto){
        return importService.createImport(createImportDto);
    }

    @PatchMapping("/update/{uuid}")
    public ImportDto updateImportByUuid(@PathVariable String uuid, @RequestBody @Valid UpdateImportDto updateImportDto){
        return importService.updateImportByUuid(uuid, updateImportDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void deleteImportByUuid(@PathVariable String uuid){
        importService.deleteImportByUuid(uuid);
    }

    @GetMapping("/uuid/{uuid}")
    public ImportDto findByUuid(@PathVariable String uuid){
        return importService.findByUuid(uuid);
    }

    @GetMapping("/import_date/{date}")
    public List<ImportDto> findAllByImportDate(@PathVariable LocalDate date){
        return importService.findAllByImportDate(date);
    }

    @GetMapping
    public List<ImportDto> findAll(){
        return importService.findAll();
    }

}
