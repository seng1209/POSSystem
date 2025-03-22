package com.example.possystem.api.service.customer;

import com.example.possystem.api.mapper.CustomerMapper;
import com.example.possystem.api.model.customer.Customer;
import com.example.possystem.api.model.customer.dto.CreateCustomerDto;
import com.example.possystem.api.model.customer.dto.CustomerDto;
import com.example.possystem.api.model.customer.dto.UpdateCustomerDto;
import com.example.possystem.api.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {

        if (customerRepository.existsByPhone(createCustomerDto.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone ready exist.");
        }

        if (customerRepository.existsByEmail(createCustomerDto.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ready exist.");
        }

        Customer customer = customerMapper.fromCustomerDto(createCustomerDto);
        customer.setUuid(UUID.randomUUID().toString());

        customerRepository.save(customer);

        return CustomerDto.builder()
                .uuid(customer.getUuid())
                .nameKh(customer.getNameKh())
                .nameEng(customer.getNameEng())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .customerType(customer.getCustomerType())
                .address(customer.getAddress())
                .build();
    }

    @Override
    public CustomerDto updateCustomerByPhone(String phone, UpdateCustomerDto updateCustomerDto) {
        if (customerRepository.existsByPhone(phone)){
            Customer customer = customerRepository.findByPhone(phone).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found.")
            );

            customerMapper.fromUpdateCustomerDto(customer, updateCustomerDto);

            customerRepository.save(customer);

            return CustomerDto.builder()
                    .uuid(customer.getUuid())
                    .nameKh(customer.getNameKh())
                    .nameEng(customer.getNameEng())
                    .phone(customer.getPhone())
                    .email(customer.getEmail())
                    .customerType(customer.getCustomerType())
                    .address(customer.getAddress())
                    .build();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found.");
    }

    @Override
    public void deleteCustomerByPhone(String phone) {
        Customer customer = customerRepository.findByPhone(phone).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found.")
        );

        customerRepository.delete(customer);
    }

    @Override
    public CustomerDto findByPhone(String phone) {
        Customer customer = customerRepository.findByPhone(phone).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found.")
        );
        return customerMapper.toCustomerDto(customer);
    }

    @Override
    public CustomerDto findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found.")
        );
        return customerMapper.toCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerMapper.toCustomerDtoList(customerList);
    }
}
