package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.entities.Cart;
import com.oussama.eshop.mappers.Mapper;
import com.oussama.eshop.repositories.CartRepository;
import com.oussama.eshop.services.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;
    private final Mapper<Cart, CartDto> mapper;

    public CartServiceImpl(CartRepository cartRepository, Mapper<Cart, CartDto> mapper) {
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    @Override
    public CartDto save(CartDto cart) {
        return mapper.mapTo(mapper.mapFrom(cart));
    }

    @Override
    public List<CartDto> findAll() {
        return cartRepository.findAll().stream().map(mapper::mapTo).toList();
    }

    @Override
    public CartDto findOne(Integer id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + id));
        return mapper.mapTo(cart);
    }

    @Override
    public void delete(Integer id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        } else throw new EntityNotFoundException("Cart not found with id: " + id);
    }

    @Override
    public CartDto fullUpdate(CartDto cart) {
        if (cartRepository.existsById(cart.getId())) {
            return mapper.mapTo(cartRepository.save(mapper.mapFrom(cart)));
        } else throw new EntityNotFoundException("Cart not found with id: " + cart.getId());
    }

    @Override
    public CartDto partialUpdate(CartDto cart) {
        if (cartRepository.existsById(cart.getId())) {
            return mapper.mapTo(cartRepository.save(mapper.mapFrom(cart)));
        } else throw new EntityNotFoundException("Cart not found with id: " + cart.getId());
    }

    @Override
    public boolean exists(Integer id) {
        return cartRepository.existsById(id);
    }

    @Override
    public CartDto findCartByUserId(Integer id) {
        Cart cart = cartRepository.findByCustomerId(id);
        return mapper.mapTo(cart);
    }


}
