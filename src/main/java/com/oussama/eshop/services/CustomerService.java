package com.oussama.eshop.services;

import com.oussama.eshop.domain.dto.requests.FindRequest;
import com.oussama.eshop.domain.dto.CustomerDto;

import java.util.List;

public interface CustomerService {


    List<CustomerDto> findAll();

    CustomerDto findOne(Integer id);
    void delete(Integer id);

    CustomerDto fullUpdate(CustomerDto customer);

    CustomerDto partialUpdate(CustomerDto customer);

    boolean exists(Integer id);

    CustomerDto findByEmail(String email);

    List<CustomerDto> findDynamicCustomers(FindRequest findRequest);
}
