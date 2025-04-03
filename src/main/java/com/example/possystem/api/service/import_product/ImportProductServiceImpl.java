package com.example.possystem.api.service.import_product;

import com.example.possystem.api.mapper.ImportProductMapper;
import com.example.possystem.api.model.import_product.ImportProduct;
import com.example.possystem.api.model.import_product.dto.CreateImportProductDto;
import com.example.possystem.api.model.import_product.dto.ImportProductDto;
import com.example.possystem.api.model.import_product.dto.UpdateImportProductDto;
import com.example.possystem.api.model.imports.Import;
import com.example.possystem.api.model.product.Product;
import com.example.possystem.api.repository.ImportProductRepository;
import com.example.possystem.api.repository.ImportRepository;
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
public class ImportProductServiceImpl implements ImportProductService{

    private final ImportProductMapper importProductMapper;
    private final ImportProductRepository importProductRepository;
    private final ImportRepository importRepository;
    private final ProductRepository productRepository;

    @Override
    public ImportProductDto createImportProduct(CreateImportProductDto createImportProductDto) {

        Import imports = importRepository.findByUuid(createImportProductDto.importUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import UUID not found.")
        );

        Product product = productRepository.findByUuid(createImportProductDto.productUuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product UUID not found.")
        );

        ImportProduct importProduct = importProductMapper.fromImportProductDto(createImportProductDto);
        importProduct.setUuid(UUID.randomUUID().toString());
        importProduct.setProducts(product);
        importProduct.setImports(imports);
        importProduct.setIsDeleted(false);

        importProductRepository.save(importProduct);

        return importProductMapper.toImportProductDto(importProduct);
    }

    @Override
    public ImportProductDto updateByUuid(String uuid, UpdateImportProductDto updateImportProductDto) {

        if (importProductRepository.existsByUuid(uuid)){
            ImportProduct importProduct = importProductRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import Product not found.")
            );

            importProductMapper.fromUpdateImportProductDto(importProduct, updateImportProductDto);

            if (updateImportProductDto.importUuid() != null){
                Import newImport = importRepository.findByUuid(updateImportProductDto.importUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import not found.")
                );
                importProduct.setImports(newImport);
            }

            if (updateImportProductDto.productUuid() != null){
                Product newProduct = productRepository.findByUuid(updateImportProductDto.productUuid()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.")
                );
                importProduct.setProducts(newProduct);
            }

            importProductRepository.save(importProduct);

            return importProductMapper.toImportProductDto(importProduct);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Import Product not found.");
    }

    @Transactional
    @Override
    public void deleteByUuid(String uuid) {
        if (importProductRepository.existsByUuid(uuid)){
            importProductRepository.deleteByUuid(uuid);
            return;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Import Product not found.");
    }

    @Override
    public List<ImportProductDto> findAll() {
        List<ImportProduct> importProductList = importProductRepository.findAllByIsDeletedIsFalse();
        return importProductMapper.ImportProductDtoList(importProductList);
    }

    @Override
    public ImportProductDto findByUuid(String uuid) {
        ImportProduct importProduct = importProductRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Import Product not found.")
        );
        return importProductMapper.toImportProductDto(importProduct);
    }

    @Override
    public List<ImportProductDto> findAllByImportUuid(String importUuid) {
        List<ImportProduct> importProductList = importProductRepository.findAllByImportsUuidAndIsDeletedIsFalse(importUuid);
        return importProductMapper.ImportProductDtoList(importProductList);
    }
}
