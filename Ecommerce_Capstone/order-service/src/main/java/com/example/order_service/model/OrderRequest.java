package com.example.order_service.model;

import lombok.Data;

@Data
public class OrderRequest {
    private Integer quantity;
    private Long productId;
}
