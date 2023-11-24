package com.example.booking_restaurant.repository;

import com.example.booking_restaurant.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
