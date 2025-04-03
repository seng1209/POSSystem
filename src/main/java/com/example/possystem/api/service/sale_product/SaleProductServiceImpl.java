package com.example.possystem.api.service.sale_product;

import com.example.possystem.api.mapper.SaleProductMapper;
import com.example.possystem.api.model.product.Product;
import com.example.possystem.api.model.sale.Sale;
import com.example.possystem.api.model.sale_product.SaleProduct;
import com.example.possystem.api.model.sale_product.dto.CreateSaleProductDto;
import com.example.possystem.api.model.sale_product.dto.SaleProductDto;
import com.example.possystem.api.model.sale_product.dto.UpdateSaleProductDto;
import com.example.possystem.api.repository.ProductRepository;
import com.example.possystem.api.repository.SaleProductRepository;
import com.example.possystem.api.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleProductServiceImpl implements SaleProductService {

    private final SaleProductMapper saleProductMapper;
    private final SaleProductRepository saleProductRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Override
    public SaleProductDto createSaleProduct(CreateSaleProductDto createSaleProductDto) {

        Sale sale = saleRepository.findByUuid(createSaleProductDto.saleUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale UUID not found.")
        );

        Product product = productRepository.findByUuid(createSaleProductDto.productUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product UUID not found.")
        );

        SaleProduct saleProduct = saleProductMapper.fromSaleProductDto(createSaleProductDto);
        saleProduct.setUuid(UUID.randomUUID().toString());
        saleProduct.setSales(sale);
        saleProduct.setProducts(product);
        saleProduct.setIsDeleted(false);

        saleProductRepository.save(saleProduct);

        return saleProductMapper.toSaleProductDto(saleProduct);
    }

    @Override
    public SaleProductDto updateByUuid(String uuid, UpdateSaleProductDto updateSaleProductDto) {

        if (saleProductRepository.existsByUuid(uuid)){
            SaleProduct saleProduct = saleProductRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found.")
            );

            if (updateSaleProductDto.productUuid() != null){
                Product newProduct = productRepository.findByUuid(updateSaleProductDto.productUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.")
                );
                saleProduct.setProducts(newProduct);
            }

            if (updateSaleProductDto.saleUuid() != null){
                Sale newSale = saleRepository.findByUuid(updateSaleProductDto.saleUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found.")
                );
                saleProduct.setSales(newSale);
            }

            saleProductMapper.fromUpdateSaleProductDto(saleProduct, updateSaleProductDto);

            saleProductRepository.save(saleProduct);

            return saleProductMapper.toSaleProductDto(saleProduct);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID not found.");
    }

    @Transactional
    @Override
    public void deleteByUuid(String uuid) {
        if (saleProductRepository.existsByUuid(uuid)){
            saleProductRepository.deleteByUuid(uuid);
            return;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID not found.");
    }

    @Override
    public List<SaleProductDto> findAll() {
        List<SaleProduct> saleProductList = saleProductRepository.findAllByIsDeletedIsFalse();
        return saleProductMapper.toSaleProductDtoList(saleProductList);
    }

    @Override
    public SaleProductDto findByUuid(String uuid) {
        SaleProduct saleProduct = saleProductRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID not found.")
        );
        return saleProductMapper.toSaleProductDto(saleProduct);
    }

    @Override
    public List<SaleProductDto> findAllBySaleUuid(String saleUuid) {
        List<SaleProduct> saleProductList = saleProductRepository.findAllBySaleUuidAndIsDeletedIsFalse(saleUuid);
        return saleProductMapper.toSaleProductDtoList(saleProductList);
    }
}
