package com.example.possystem.api.service.customer;

import com.example.possystem.api.model.customer.dto.CreateCustomerDto;
import com.example.possystem.api.model.customer.dto.CustomerDto;
import com.example.possystem.api.model.customer.dto.UpdateCustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CreateCustomerDto createCustomerDto);

    CustomerDto updateCustomerByPhone(String phone, UpdateCustomerDto updateCustomerDto);

    void deleteCustomerByPhone(String phone);

    CustomerDto findByPhone(String phone);

    CustomerDto findByEmail(String email);

    List<CustomerDto> findAll();

}
