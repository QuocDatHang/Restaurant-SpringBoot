package com.example.booking_restaurant.controller.restController;

import com.example.booking_restaurant.domain.Cart;
import com.example.booking_restaurant.domain.CartDetail;
import com.example.booking_restaurant.domain.Customer;
import com.example.booking_restaurant.service.cartDetailService.response.CartDetailResponse;
import com.example.booking_restaurant.service.cartDetailService.CartDetailService;
import com.example.booking_restaurant.service.cartService.request.CartRequest;
import com.example.booking_restaurant.service.cartService.CartService;
import com.example.booking_restaurant.service.cartService.response.CartResponse;
import com.example.booking_restaurant.service.customerService.CustomerService;
import com.example.booking_restaurant.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/carts")
@AllArgsConstructor
public class CartRestController {
    private CartService cartService;
    private CartDetailService cartDetailService;
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllItems() {
        String email = AppUtil.getPrincipalUsername();

        Customer customer = customerService.findByEmail(email);

        Optional<Cart> cartOptional = cartService.findByCustomer(customer);

        if (cartOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CartDetail> cartDetailList = cartDetailService.findAllByCartAndCustomer(cartOptional.get(), customer);

        List<CartDetailResponse> cartDetailResponses = cartDetailService.findAllCartDetailResponsesByCart(cartOptional.get());

        if (cartDetailList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        CartResponse cartResponse = new CartResponse();
        cartResponse.setTotalPrice(cartOptional.get().getTotalPrice());
        cartResponse.setCartDetailList(cartDetailResponses);

        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @PostMapping("add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest) {
        String email = AppUtil.getPrincipalUsername();
        cartService.addToCart(cartRequest, email);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
