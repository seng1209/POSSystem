package com.example.possystem.api.repository;

import com.example.possystem.api.model.imports.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ImportRepository extends JpaRepository<Import, Long> {

    Boolean existsByUuid(String uuid);

    Optional<Import> findByUuid(String uuid);

    List<Import> findAllByIsDeletedIsFalse();

    List<Import> findAllByImportDateAndIsDeletedIsFalse(LocalDate importDate);

    @Modifying
    @Query("UPDATE Import AS I SET I.isDeleted = true WHERE I.uuid = ?1")
    void deleteImportByUuid(String uuid);

}
