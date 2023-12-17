package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.dto.CustomerDto;
import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.Customer;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.services.CartService;
import com.oussama.eshop.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CartService cartService;
    private final Mapper<Cart, CartDto> cartMapper;


    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestBody Integer id){
        customerService.delete(id);
        return new ResponseEntity<>("Customer with id "+id+" has been deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<CustomerDto> getCurrentCustomer(Authentication auth){
        return new ResponseEntity<>(customerService.findByEmail(auth.getName()), HttpStatus.OK);
    }
}
