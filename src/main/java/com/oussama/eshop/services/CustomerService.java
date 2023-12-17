package com.oussama.eshop.services;

import com.oussama.eshop.domain.dto.CustomerDto;
import com.oussama.eshop.domain.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {


    List<CustomerDto> findAll();

    CustomerDto findOne(Integer id);
    void delete(Integer id);

    CustomerDto fullUpdate(CustomerDto customer);

    CustomerDto partialUpdate(CustomerDto customer);

    boolean exists(Integer id);

    CustomerDto findByEmail(String email);
}
