package com.oussama.eshop.repositories;

import com.oussama.eshop.domain.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT cart FROM Customer c WHERE c.id = :id")
    Cart findByCustomerId(Integer id);
}
