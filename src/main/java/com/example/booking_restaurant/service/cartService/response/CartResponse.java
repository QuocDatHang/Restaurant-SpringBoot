package com.example.booking_restaurant.service.cartService.response;

import com.example.booking_restaurant.service.cartDetailService.response.CartDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    private BigDecimal totalPrice;

    private List<CartDetailResponse> cartDetailList;
}
