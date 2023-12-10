package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.dto.CustomerDto;
import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.Customer;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.CustomerRepository;
import com.oussama.eshop.services.CartService;
import com.oussama.eshop.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CartService cartService;
    private final Mapper<Customer, CustomerDto> mapper;
    private final Mapper<Cart, CartDto> cartMapper;

    public CustomerServiceImpl(CustomerRepository repository, CartService cartService, Mapper<Customer, CustomerDto> mapper, Mapper<Cart, CartDto> cartMapper) {
        this.repository = repository;
        this.cartService = cartService;
        this.mapper = mapper;
        this.cartMapper = cartMapper;
    }

    @Override
    public CustomerDto create(CustomerDto customer) {
        Customer newCustomer = mapper.mapFrom(customer);
        newCustomer.setCart(cartMapper.mapFrom(cartService.save(cartMapper.mapTo(new Cart()))));
        return mapper.mapTo(repository.save(newCustomer));
    }

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
}
