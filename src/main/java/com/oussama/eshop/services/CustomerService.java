package com.oussama.eshop.services;

import com.oussama.eshop.domain.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findOne(Integer id);
    void delete(Integer id);

    Customer update(Customer customer);
}
