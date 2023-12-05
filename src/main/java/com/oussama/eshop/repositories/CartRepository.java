package com.oussama.eshop.repositories;

import com.oussama.eshop.domain.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
