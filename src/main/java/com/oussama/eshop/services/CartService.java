package com.oussama.eshop.services;

import com.oussama.eshop.domain.dto.CartDto;
import com.oussama.eshop.domain.entities.Cart;


import java.util.List;
import java.util.Optional;

public interface CartService {

    CartDto save(CartDto cart);

    List<CartDto> findAll();

    CartDto findOne(Integer id);

    void delete(Integer id);

    CartDto fullUpdate(CartDto cart);

    CartDto partialUpdate(CartDto cart);

    boolean exists(Integer id);
}
