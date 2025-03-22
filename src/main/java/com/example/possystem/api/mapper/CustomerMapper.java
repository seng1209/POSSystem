package com.example.possystem.api.mapper;

import com.example.possystem.api.model.customer.Customer;
import com.example.possystem.api.model.customer.dto.CreateCustomerDto;
import com.example.possystem.api.model.customer.dto.CustomerDto;
import com.example.possystem.api.model.customer.dto.UpdateCustomerDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer fromCustomerDto(CreateCustomerDto createCustomerDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateCustomerDto(@MappingTarget Customer customer, UpdateCustomerDto updateCustomerDto);

    CustomerDto toCustomerDto(Customer customer);

    List<CustomerDto> toCustomerDtoList(List<Customer> customerList);

}
