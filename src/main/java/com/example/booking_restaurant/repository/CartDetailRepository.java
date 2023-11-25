package com.example.booking_restaurant.repository;

import com.example.booking_restaurant.domain.Cart;
import com.example.booking_restaurant.domain.CartDetail;
import com.example.booking_restaurant.domain.Customer;
import com.example.booking_restaurant.domain.Food;
import com.example.booking_restaurant.service.cartDetailService.response.CartDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findByFoodAndCart(Food food, Cart cart);

    @Query("SELECT SUM(cd.amount) FROM CartDetail AS cd WHERE cd.cart = :cart")
    BigDecimal sumAmountByCart(@Param("cart") Cart cart);

    @Query("SELECT cd " +
            "FROM CartDetail AS cd " +
            "JOIN Cart AS cart " +
            "ON cd.cart = cart " +
            "AND cd.cart = :cart " +
            "AND cd.cart.customer = :customer"
    )
    List<CartDetail> findAllByCartAndCustomer(@Param("cart") Cart cart, @Param("customer") Customer customer);

    @Query("SELECT NEW com.example.booking_restaurant.service.cartDetailService.response.CartDetailResponse (" +
            "cd.id, " +
            "cd.food.id, " +
            "cd.food.foodName, " +
            "cd.food.images, " +
            "cd.price, " +
            "cd.quantity, " +
            "cd.amount" +
            ") FROM CartDetail AS cd " +
            "WHERE cd.cart = :cart"
    )
    List<CartDetailResponse> findAllCartItemResDTOByCart(@Param("cart") Cart cart);
}
