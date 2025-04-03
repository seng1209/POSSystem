package com.example.possystem.api.service.sale_product;

import com.example.possystem.api.model.sale_product.dto.CreateSaleProductDto;
import com.example.possystem.api.model.sale_product.dto.SaleProductDto;
import com.example.possystem.api.model.sale_product.dto.UpdateSaleProductDto;

import java.util.List;

public interface SaleProductService {

    // create
    SaleProductDto createSaleProduct(CreateSaleProductDto createSaleProductDto);

    // update by uuid
    SaleProductDto updateByUuid(String uuid, UpdateSaleProductDto updateSaleProductDto);

    // delete by uuid
    void deleteByUuid(String uuid);

    // get all
    List<SaleProductDto> findAll();

    // get by uuid
    SaleProductDto findByUuid(String uuid);

    // get all by sale uuid
    List<SaleProductDto> findAllBySaleUuid(String saleUuid);

}
