package com.example.booking_restaurant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Table(name = "bill_details")
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productTitle;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private Integer quantity;

    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "id", nullable = false)
    private Bill bill;
}
