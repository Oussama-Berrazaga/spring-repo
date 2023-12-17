package com.oussama.eshop.services;

import com.oussama.eshop.domain.entities.CartProduct;

import java.util.Optional;
import java.util.List;
public interface CartProductService {

    CartProduct save(CartProduct cartProduct);

    List<CartProduct> findCartProductByCartId(Integer cartId);

    List<CartProduct> findAll();

    List<CartProduct> findCartProductsByCustomer(Integer userId);
}
