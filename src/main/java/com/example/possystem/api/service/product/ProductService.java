package com.example.possystem.api.service.product;

import com.example.possystem.api.model.product.dto.CreateProductDto;
import com.example.possystem.api.model.product.dto.ProductDto;
import com.example.possystem.api.model.product.dto.UpdateProductDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(CreateProductDto createProductDto);

    ProductDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto);

    @Query("UPDATE Product AS P SET P.isDeleted = true ")
    void deleteProductByUuid(String uuid);

    List<ProductDto> findAll();

    ProductDto findByUuid(String uuid);

    List<ProductDto> findByCategory(String category);
}
