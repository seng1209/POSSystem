package com.example.possystem.api.controller;

import com.example.possystem.api.model.sale.dto.CreateSaleDto;
import com.example.possystem.api.model.sale.dto.SaleDto;
import com.example.possystem.api.model.sale.dto.UpdateSaleDto;
import com.example.possystem.api.service.sale.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SaleDto createSale(@RequestBody @Valid CreateSaleDto createSaleDto){
        return saleService.createSale(createSaleDto);
    }

    @PatchMapping("/{uuid}")
    public SaleDto updateByUuid(@PathVariable String uuid, @RequestBody UpdateSaleDto updateSaleDto){
        return saleService.updateByUuid(uuid, updateSaleDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void deleteByUuid(@PathVariable String uuid){
        saleService.deleteByUuid(uuid);
    }

    @GetMapping
    public List<SaleDto> findAll(){
        return saleService.findAll();
    }

    @GetMapping("/{uuid}")
    public SaleDto findByUuid(@PathVariable String uuid){
        return saleService.findByUuid(uuid);
    }

    @GetMapping("/date/{saleDate}")
    public List<SaleDto> findByDate(@PathVariable LocalDate saleDate){
        return saleService.findByDate(saleDate);
    }

}
