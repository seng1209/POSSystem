package com.example.possystem.api.service.product;

import com.example.possystem.api.mapper.CategoryMapper;
import com.example.possystem.api.mapper.ProductMapper;
import com.example.possystem.api.model.category.Category;
import com.example.possystem.api.model.product.Product;
import com.example.possystem.api.model.product.dto.CreateProductDto;
import com.example.possystem.api.model.product.dto.ProductDto;
import com.example.possystem.api.model.product.dto.UpdateProductDto;
import com.example.possystem.api.repository.CategoryRepository;
import com.example.possystem.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ProductDto createProduct(CreateProductDto createProductDto) {

        Category category = categoryRepository.findByUuid(createProductDto.categoryUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category noy found.")
        );

        Product product = productMapper.fromProductDto(createProductDto);
        product.setUuid(UUID.randomUUID().toString());
        product.setCategory(category);
        product.setInStock(true);
        product.setIsDeleted(false);

        productRepository.save(product);

        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto) {
        if (productRepository.existsByUuid(uuid)){
            Product product = productRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.")
            );
            productMapper.formUpdateProductDto(product, updateProductDto);

            if (updateProductDto.categoryUuid() != null){
                Category newCategory = categoryRepository.findByUuid(updateProductDto.categoryUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.")
                );
                product.setCategory(newCategory);
            }

            productRepository.save(product);

            return productMapper.toProductDto(product);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
    }

    @Transactional
    @Override
    public void deleteProductByUuid(String uuid) {
        if (productRepository.existsByUuid(uuid)){
            productRepository.updateByIsDeleted(uuid);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> productList = productRepository.findAllByIsDeletedIsFalse();
        return productMapper.toProductDtoList(productList);
    }

    @Override
    public ProductDto findByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.")
        );
        return productMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> findByCategory(String category) {
        List<Product> productList = productRepository.findAllByCategory(category);
        return productMapper.toProductDtoList(productList);
//        return null;
    }
}
