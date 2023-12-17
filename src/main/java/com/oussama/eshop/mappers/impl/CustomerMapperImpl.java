package com.oussama.eshop.mappers.impl;

import com.oussama.eshop.domain.dto.CustomerDto;
import com.oussama.eshop.domain.entities.Customer;
import com.oussama.eshop.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class CustomerMapperImpl implements Mapper<Customer, CustomerDto> {
    private final ModelMapper modelMapper;

    @Override
    public CustomerDto mapTo(Customer customer) {
        return modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public Customer mapFrom(CustomerDto customerDto) {
        return modelMapper.map(customerDto,Customer.class);
    }
}
