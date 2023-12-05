package com.oussama.eshop.services;

import com.oussama.eshop.domain.entities.Cart;


import java.util.List;
import java.util.Optional;

public interface CartService {

    Cart save(Cart cart);

    List<Cart> findAll();

    Optional<Cart> findOne(Integer id);

    void delete(Integer id);

    Cart update(Cart cart);

    boolean exists(Integer id);
}
