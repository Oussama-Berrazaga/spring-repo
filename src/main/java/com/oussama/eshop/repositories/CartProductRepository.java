package com.oussama.eshop.repositories;

import com.oussama.eshop.domain.entities.CartProduct;
import com.oussama.eshop.domain.entities.CartProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {
}
