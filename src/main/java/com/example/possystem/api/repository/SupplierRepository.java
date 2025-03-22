package com.example.possystem.api.repository;

import com.example.possystem.api.model.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Boolean existsByContactPhone(String contactPhone);

    Optional<Supplier> findByContactPhone(String contactPhone);

    Boolean existsByUuid(String uuid);

    Optional<Supplier> findByUuid(String uuid);

}
