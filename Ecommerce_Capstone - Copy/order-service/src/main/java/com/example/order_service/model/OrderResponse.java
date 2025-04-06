package com.example.order_service.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private String status;
}
