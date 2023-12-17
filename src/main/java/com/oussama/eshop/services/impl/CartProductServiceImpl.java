package com.oussama.eshop.services.impl;

import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.entities.CartProduct;
import com.oussama.eshop.repositories.CartProductRepository;
import com.oussama.eshop.services.CartProductService;
import com.oussama.eshop.services.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Service
public class CartProductServiceImpl implements CartProductService {

    private final CartProductRepository cartProductRepository;
    private final CartService cartService;

    public CartProductServiceImpl(CartProductRepository cartProductRepository, CartService cartService) {
        this.cartProductRepository = cartProductRepository;
        this.cartService = cartService;
    }

    @Override
    public CartProduct save(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }


    @Override
    public List<CartProduct> findCartProductByCartId(Integer cartId) {

        List<CartProduct> cartProductList = cartProductRepository.findAll();
        return cartProductList.stream().filter(cartProduct -> Objects.equals(cartProduct.getCart().getId(), cartId)).toList();

    }

    @Override
    public List<CartProduct> findAll() {
        return cartProductRepository.findAll();
    }

    @Override
    public List<CartProduct> findCartProductsByCustomer(Integer userId) {
        CartDto cartDto = cartService.findCartByUserId(userId);
        return findCartProductByCartId(cartDto.getId());
    }
}
