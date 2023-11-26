package com.example.booking_restaurant.service.cartDetailService;

import com.example.booking_restaurant.domain.Cart;
import com.example.booking_restaurant.domain.CartDetail;
import com.example.booking_restaurant.domain.Customer;
import com.example.booking_restaurant.repository.CartDetailRepository;
import com.example.booking_restaurant.service.cartDetailService.response.CartDetailResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class CartDetailService {

    private CartDetailRepository cartDetailRepository;

    public List<CartDetail> findAllByCartAndCustomer(Cart cart, Customer customer) {
        return cartDetailRepository.findAllByCartAndCustomer(cart, customer);
    }
    public List<CartDetailResponse> findAllCartDetailResponsesByCart(Cart cart) {
        List<CartDetailResponse> cartDetailResponses = cartDetailRepository.findAllCartDetailResponseByCart(cart);
        return cartDetailResponses;
    }
}
