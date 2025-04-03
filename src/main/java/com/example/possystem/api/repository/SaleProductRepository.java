package com.example.possystem.api.repository;

import com.example.possystem.api.model.sale_product.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {

    Boolean existsByUuid(String uuid);

    Optional<SaleProduct> findByUuid(String uuid);

    List<SaleProduct> findAllByIsDeletedIsFalse();

    @Query("SELECT SP FROM SaleProduct AS SP WHERE SP.sales.uuid = ?1")
    List<SaleProduct> findAllBySaleUuidAndIsDeletedIsFalse(String saleUuid);

    @Modifying
    @Query("UPDATE SaleProduct SET isDeleted = true WHERE uuid = ?1")
    void deleteByUuid(String uuid);

}
