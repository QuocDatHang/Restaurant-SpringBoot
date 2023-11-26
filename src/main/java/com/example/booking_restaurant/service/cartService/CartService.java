package com.example.booking_restaurant.service.cartService;

import com.example.booking_restaurant.domain.Cart;
import com.example.booking_restaurant.domain.CartDetail;
import com.example.booking_restaurant.domain.Customer;
import com.example.booking_restaurant.domain.Food;
import com.example.booking_restaurant.exception.DataInputException;
import com.example.booking_restaurant.repository.CartDetailRepository;
import com.example.booking_restaurant.repository.CartRepository;
import com.example.booking_restaurant.repository.FoodRepository;
import com.example.booking_restaurant.service.cartService.request.CartRequest;
import com.example.booking_restaurant.service.customerService.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private CartDetailRepository cartDetailRepository;
    private CustomerService customerService;
    private FoodRepository foodRepository;

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findByCustomer(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    public void addToCart(CartRequest cartRequest, String email) {

        Customer customer = customerService.findByEmail(email);
        Optional<Cart> cartOptional = cartRepository.findByCustomer(customer);

        Food food = foodRepository.findById(cartRequest.getFoodId()).orElseThrow( () -> {
            throw new DataInputException("Food not found");
        });
        Integer quantity = 1;
        BigDecimal price = food.getPrice();
        BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

        if (cartOptional.isEmpty()) {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setTotalPrice(BigDecimal.ZERO);
            cartRepository.save(cart);

            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setFood(food);
            cartDetail.setFoodName(food.getFoodName());
            cartDetail.setQuantity(quantity);
            cartDetail.setPrice(price);
            cartDetail.setAmount(amount);
            cartDetailRepository.save(cartDetail);

            cart.setTotalPrice(amount);
            cartRepository.save(cart);
        }
        else {
            Cart cart = cartOptional.get();
            Optional<CartDetail> cartDetailOptional = cartDetailRepository.findByFoodAndCart(food, cart);

            if (cartDetailOptional.isEmpty()) {
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setFood(food);
                cartDetail.setFoodName(food.getFoodName());
                cartDetail.setQuantity(quantity);
                cartDetail.setPrice(price);
                cartDetail.setAmount(amount);
                cartDetailRepository.save(cartDetail);
            }
            else {
                CartDetail cartDetail = cartDetailOptional.get();
                Integer currentQuantity = cartDetail.getQuantity();
                Integer newQuantity = currentQuantity + 1;
                amount = price.multiply(BigDecimal.valueOf(newQuantity));
                cartDetail.setAmount(amount);
                cartDetail.setQuantity(newQuantity);
                cartDetailRepository.save(cartDetail);
            }

            BigDecimal totalPrice = cartDetailRepository.sumAmountByCart(cart);
            cart.setTotalPrice(totalPrice);
            cartRepository.save(cart);
        }

    }
}
