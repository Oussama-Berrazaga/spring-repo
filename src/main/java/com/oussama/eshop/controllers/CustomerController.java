package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.dto.CustomerDto;
import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.Customer;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.services.CartService;
import com.oussama.eshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CartService cartService;
    private final Mapper<Cart, CartDto> cartMapper;

    public CustomerController(CustomerService customerService, CartService cartService, Mapper<Cart, CartDto> cartMapper) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer){
        return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestBody Integer id){
        customerService.delete(id);
        return new ResponseEntity<>("Customer with id "+id+" has been deleted successfully",HttpStatus.OK);
    }
}
