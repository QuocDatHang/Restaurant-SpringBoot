package com.example.booking_restaurant.service.cartDetailService.response;

import com.example.booking_restaurant.domain.FoodImage;
import com.example.booking_restaurant.service.foodImageService.response.FoodImageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDetailResponse {
    private Long id;
    private Long foodId;
    private String foodName;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal amount;

}
