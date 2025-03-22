package com.example.possystem.api.service.category;

import com.example.possystem.api.model.category.dto.CategoryDto;
import com.example.possystem.api.model.category.dto.CreateCategoryDto;
import com.example.possystem.api.model.category.dto.UpdateCategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CreateCategoryDto createCategoryDto);

    CategoryDto updateCategory(String uuid, UpdateCategoryDto updateCategoryDto);

    void deleteCategoryByUuid(String uuid);

    CategoryDto findByCategory(String categoryName);

    CategoryDto findByUuid(String uuid);

    List<CategoryDto> findAll();

}
