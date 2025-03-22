package com.example.possystem.api.repository;

import com.example.possystem.api.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByUuid(String uuid);

    Boolean existsByUuid(String uuid);

    @Modifying
    @Query("UPDATE Product AS P SET P.isDeleted = true WHERE P.uuid = ?1")
    void updateByIsDeleted(String uuid);

    @Query("SELECT P FROM Product AS P WHERE P.category.categoryName = ?1")
    List<Product> findAllByCategory(String category);

//    @Query("SELECT P FROM Product AS P WHERE P.isDeleted = false ")
    List<Product> findAllByIsDeletedIsFalse();

}
