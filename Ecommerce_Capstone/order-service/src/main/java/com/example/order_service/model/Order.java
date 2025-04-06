package com.example.order_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @Positive(message = "Price must be positive")
    private Double totalPrice;

    private LocalDateTime orderDate;

    private Long productId;

}
