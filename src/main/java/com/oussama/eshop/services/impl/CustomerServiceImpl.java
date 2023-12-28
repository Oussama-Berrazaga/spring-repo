package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.dto.requests.FindRequest;
import com.oussama.eshop.domain.dto.CustomerDto;
import com.oussama.eshop.domain.entities.Customer;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.CustomerRepository;
import com.oussama.eshop.services.CustomerService;
import com.oussama.eshop.specifications.EntitySpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final Mapper<Customer, CustomerDto> mapper;
    private final EntitySpecification<Customer> entitySpecification;

    @Override
    public List<CustomerDto> findAll() {
        return repository.findAll().stream().map(mapper::mapTo).toList();
    }

    @Override
    public CustomerDto findOne(Integer id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return mapper.mapTo(customer);
    }

    @Override
    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else throw new EntityNotFoundException("Customer not found with id: " + id);
    }

    @Override
    public CustomerDto fullUpdate(CustomerDto customer) {
        if (repository.existsById(customer.getId())) {
            return mapper.mapTo(repository.save(mapper.mapFrom(customer)));
        } else throw new EntityNotFoundException("Customer not found with id: " + customer.getId());
    }

    @Override
    public CustomerDto partialUpdate(CustomerDto customer) {
        if (repository.existsById(customer.getId())) {
            return mapper.mapTo(repository.save(mapper.mapFrom(customer)));
        } else throw new EntityNotFoundException("Customer not found with id: " + customer.getId());
    }

    @Override
    public boolean exists(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public CustomerDto findByEmail(String email) {
        Customer customer = repository.findCustomerByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Customer not found with email " + email));
        return mapper.mapTo(customer);
    }
    @Override
    public List<CustomerDto> findDynamicCustomers(FindRequest req) {
        return repository.findAll(entitySpecification.specificationBuilder(req)).stream().map(mapper::mapTo).toList();
    }
}
