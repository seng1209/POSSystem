package com.example.possystem.api.repository;

import com.example.possystem.api.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Boolean existsByPhone(String phone);

    Optional<Customer> findByPhone(String phone);

    Boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);

    Boolean existsByUuid(String uuid);

    Optional<Customer> findByUuid(String uuid);

}
