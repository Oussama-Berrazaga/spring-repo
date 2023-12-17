package com.oussama.eshop.repositories;

import com.oussama.eshop.domain.entities.Customer;
import com.oussama.eshop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT u from Customer u where u.email = :email")
    Optional<Customer> findCustomerByEmail(String email);
}
