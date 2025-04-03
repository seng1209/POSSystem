package com.example.possystem.api.repository;

import com.example.possystem.api.model.import_product.ImportProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImportProductRepository extends JpaRepository<ImportProduct, Long> {

    Boolean existsByUuid(String uuid);

    Optional<ImportProduct> findByUuid(String uuid);

    List<ImportProduct> findAllByIsDeletedIsFalse();

    List<ImportProduct> findAllByImportsUuidAndIsDeletedIsFalse(String importUuid);

    @Modifying
    @Query("UPDATE ImportProduct SET isDeleted = true WHERE uuid = ?1")
    void deleteByUuid(String uuid);

}
