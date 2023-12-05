package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.repositories.CartRepository;
import com.oussama.eshop.services.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findOne(Integer id) {
        return cartRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart update(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public boolean exists(Integer id) {
        return cartRepository.existsById(id);
    }


}
