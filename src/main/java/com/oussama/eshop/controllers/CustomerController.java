package com.oussama.eshop.controllers;

import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.domain.entities.Customer;
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

    public CustomerController(CustomerService customerService, CartService cartService) {
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Cart cart = cartService.save(new Cart());
        customer.setCart(cart);
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestBody Integer id){
        customerService.delete(id);
        return new ResponseEntity<>("Customer has been deleted successfully",HttpStatus.OK);
    }
}
