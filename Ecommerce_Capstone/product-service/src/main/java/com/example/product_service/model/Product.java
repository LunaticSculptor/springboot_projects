package com.example.product_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;

    @Positive(message = "Price must be positive")
    private Double price;

    public enum Category {
        ELECTRONICS,
        FASHION,
        GROCERY
    }
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category cannot be null and must be one of: ELECTRONICS, FASHION, GROCERY")
    private Category category;

    @PositiveOrZero(message = "Stock must be non negative")
    private Integer stock;

}
