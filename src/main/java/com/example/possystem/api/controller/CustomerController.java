package com.example.possystem.api.controller;

import com.example.possystem.api.model.customer.dto.CreateCustomerDto;
import com.example.possystem.api.model.customer.dto.CustomerDto;
import com.example.possystem.api.model.customer.dto.UpdateCustomerDto;
import com.example.possystem.api.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerDto createCustomer(@RequestBody @Valid CreateCustomerDto createCustomerDto){
        return customerService.createCustomer(createCustomerDto);
    }

    @PatchMapping("/{phone}")
    public CustomerDto updateCustomerByPhone(@PathVariable String phone, @RequestBody @Valid UpdateCustomerDto updateCustomerDto){
        return customerService.updateCustomerByPhone(phone, updateCustomerDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{phone}")
    public void deleteCustomerByPhone(@PathVariable String phone){
        customerService.deleteCustomerByPhone(phone);
    }

    @GetMapping("/phone/{phone}")
    public CustomerDto findByPhone(@PathVariable String phone){
        return customerService.findByPhone(phone);
    }

    @GetMapping("/email/{email}")
    public CustomerDto findByEmail(@PathVariable String email){
        return customerService.findByEmail(email);
    }

    @GetMapping
    public List<CustomerDto> findAll(){
        return customerService.findAll();
    }

}
