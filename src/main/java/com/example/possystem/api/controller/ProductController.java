package com.example.possystem.api.controller;

import com.example.possystem.api.model.product.dto.CreateProductDto;
import com.example.possystem.api.model.product.dto.ProductDto;
import com.example.possystem.api.model.product.dto.UpdateProductDto;
import com.example.possystem.api.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductDto createProduct(@RequestBody @Valid CreateProductDto createProductDto){
        return productService.createProduct(createProductDto);
    }

    @PatchMapping("/update/{uuid}")
    public ProductDto updateProductByUuid(@PathVariable String uuid, @RequestBody @Valid UpdateProductDto updateProductDto){
        return productService.updateProductByUuid(uuid, updateProductDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/delete/{uuid}")
    public void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }

    @GetMapping
    List<ProductDto> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{uuid}")
    public ProductDto findByUuid(@PathVariable String uuid){
        return productService.findByUuid(uuid);
    }

    @GetMapping("/category/{category}")
    public List<ProductDto> findByCategory(@PathVariable String category){
        return productService.findByCategory(category);
    }

}
