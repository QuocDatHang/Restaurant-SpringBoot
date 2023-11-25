package com.example.booking_restaurant.repository;

import com.example.booking_restaurant.domain.Cart;
import com.example.booking_restaurant.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);
}
