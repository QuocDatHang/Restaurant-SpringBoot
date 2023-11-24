package com.example.booking_restaurant.service.cartService;

import com.example.booking_restaurant.domain.Cart;
import com.example.booking_restaurant.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public void create() {

    }
}
