package com.example.possystem.api.controller;

import com.example.possystem.api.model.category.dto.CategoryDto;
import com.example.possystem.api.model.category.dto.CreateCategoryDto;
import com.example.possystem.api.model.category.dto.UpdateCategoryDto;
import com.example.possystem.api.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryDto createCategoryDto){
        return categoryService.createCategory(createCategoryDto);
    }

    @PatchMapping("/{uuid}")
    public CategoryDto updateCategory(@PathVariable String uuid, @RequestBody @Valid UpdateCategoryDto updateCategoryDto){
        return categoryService.updateCategory(uuid, updateCategoryDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteCategoryByUuid(@PathVariable String uuid){
        categoryService.deleteCategoryByUuid(uuid);
    }

    @GetMapping("/category/{categoryName}")
    public CategoryDto findByCategory(@PathVariable String categoryName){
        return categoryService.findByCategory(categoryName);
    }

    @GetMapping("/{uuid}")
    public CategoryDto findByUuid(@PathVariable String uuid){
        return categoryService.findByUuid(uuid);
    }

    @GetMapping
    public List<CategoryDto> findAll(){
        return categoryService.findAll();
    }

}
