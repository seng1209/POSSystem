package com.example.possystem.api.repository;

import com.example.possystem.api.model.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    Boolean existsByUuid(String uuid);

    Optional<Sale> findByUuid(String uuid);

    List<Sale> findAllByIsDeletedIsFalse();

    List<Sale> findAllBySaleDateAndIsDeletedIsFalse(LocalDate saleDate);

    @Modifying
    @Query("UPDATE Sale SET isDeleted = true WHERE uuid = ?1")
    void deleteByUuid(String uuid);

}
