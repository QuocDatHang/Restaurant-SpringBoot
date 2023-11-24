package com.example.booking_restaurant.repository;

import com.example.booking_restaurant.domain.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
}
