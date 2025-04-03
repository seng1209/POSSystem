package com.example.possystem.api.service.sale;

import com.example.possystem.api.model.sale.Sale;
import com.example.possystem.api.model.sale.dto.CreateSaleDto;
import com.example.possystem.api.model.sale.dto.SaleDto;
import com.example.possystem.api.model.sale.dto.UpdateSaleDto;

import java.time.LocalDate;
import java.util.List;

public interface SaleService {

    // create
    SaleDto createSale(CreateSaleDto createSaleDto);

    // update by uuid
    SaleDto updateByUuid(String uuid, UpdateSaleDto updateSaleDto);

    // delete by uuid
    void deleteByUuid(String uuid);

    // find all
    List<SaleDto> findAll();

    // find by uuid
    SaleDto findByUuid(String uuid);

    // find by sale date
    List<SaleDto> findByDate(LocalDate saleDate);

}
