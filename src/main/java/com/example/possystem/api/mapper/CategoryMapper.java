package com.example.possystem.api.mapper;

import com.example.possystem.api.model.category.Category;
import com.example.possystem.api.model.category.dto.CategoryDto;
import com.example.possystem.api.model.category.dto.CreateCategoryDto;
import com.example.possystem.api.model.category.dto.UpdateCategoryDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromCategoryDto(CreateCategoryDto createCategoryDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateCategoryDto(@MappingTarget Category category, UpdateCategoryDto updateCategoryDto);

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtoList(List<Category> categoryList);

}
