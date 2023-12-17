package com.oussama.eshop.repositories;

import com.oussama.eshop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u from User u where u.email = :email")
    Optional<User> findByEmail(String email);
    List<User> findAllByOrderByIdAsc();
}
