package com.example.possystem.api.service.category;

import com.example.possystem.api.mapper.CategoryMapper;
import com.example.possystem.api.model.category.Category;
import com.example.possystem.api.model.category.dto.CategoryDto;
import com.example.possystem.api.model.category.dto.CreateCategoryDto;
import com.example.possystem.api.model.category.dto.UpdateCategoryDto;
import com.example.possystem.api.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {

        if (categoryRepository.existsByCategoryName(createCategoryDto.categoryName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category ready exist");
        }

        Category category = categoryMapper.fromCategoryDto(createCategoryDto);
        category.setUuid(UUID.randomUUID().toString());

        categoryRepository.save(category);

        return CategoryDto.builder()
                .uuid(UUID.randomUUID().toString())
                .categoryName(createCategoryDto.categoryName())
                .build();
    }

    @Override
    public CategoryDto updateCategory(String uuid, UpdateCategoryDto updateCategoryDto) {

        if (categoryRepository.existsByUuid(uuid)){
            Category category = categoryRepository.findByUuid(uuid).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.")
            );

            categoryMapper.fromUpdateCategoryDto(category, updateCategoryDto);

            return CategoryDto.builder()
                    .uuid(category.getUuid())
                    .categoryName(updateCategoryDto.categoryName())
                    .build();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.");
    }

    @Override
    public void deleteCategoryByUuid(String uuid) {
        Category category = categoryRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.")
        );
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto findByCategory(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.")
        );
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto findByUuid(String uuid) {
        Category category = categoryRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.")
        );
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.toCategoryDtoList(categoryList);
    }
}
