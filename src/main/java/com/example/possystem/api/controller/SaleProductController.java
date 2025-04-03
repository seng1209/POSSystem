package com.example.possystem.api.controller;

import com.example.possystem.api.model.sale_product.SaleProduct;
import com.example.possystem.api.model.sale_product.dto.CreateSaleProductDto;
import com.example.possystem.api.model.sale_product.dto.SaleProductDto;
import com.example.possystem.api.model.sale_product.dto.UpdateSaleProductDto;
import com.example.possystem.api.service.sale_product.SaleProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales_products")
@RequiredArgsConstructor
public class SaleProductController {

    private final SaleProductService saleProductService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SaleProductDto createSaleProduct(@RequestBody @Valid CreateSaleProductDto createSaleProductDto){
        return saleProductService.createSaleProduct(createSaleProductDto);
    }

    @PatchMapping("/{uuid}")
    public SaleProductDto updateByUuid(@PathVariable String uuid, @RequestBody @Valid UpdateSaleProductDto updateSaleProductDto){
        return saleProductService.updateByUuid(uuid, updateSaleProductDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}")
    public void deleteByUuid(@PathVariable String uuid){
        saleProductService.deleteByUuid(uuid);
    }

    @GetMapping
    public List<SaleProductDto> findAll(){
        return saleProductService.findAll();
    }

    @GetMapping("/{uuid}")
    public SaleProductDto findByUuid(@PathVariable String uuid){
        return saleProductService.findByUuid(uuid);
    }

    @GetMapping("/sale/{saleUuid}")
    public List<SaleProductDto> findAllBySaleUuid(@PathVariable String saleUuid){
        return saleProductService.findAllBySaleUuid(saleUuid);
    }

}
