package com.example.booking_restaurant.repository;

import com.example.booking_restaurant.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
