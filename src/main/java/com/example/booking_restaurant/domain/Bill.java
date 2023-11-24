package com.example.booking_restaurant.domain;

import com.example.booking_restaurant.domain.enumration.EBookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "bills")
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createAt;
    private BigDecimal paymentPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private EBookingStatus status;
}
