package com.example.possystem.api.repository;

import com.example.possystem.api.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsByUuid(String uuid);
    Optional<Category> findByUuid(String uuid);

    Boolean existsByCategoryName(String categoryName);

    Optional<Category> findByCategoryName(String categoryName);
}
