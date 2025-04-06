package com.example.order_service.controller;

import com.example.order_service.exception.IdNotFoundException;
import com.example.order_service.exception.InvalidOrderException;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderRequest;
import com.example.order_service.model.OrderResponse;
import com.example.order_service.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) throws IdNotFoundException, InvalidOrderException {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }

    @GetMapping("/getOrders")
    public ResponseEntity<Page<Order>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "orderId") String sortBy) {
        return ResponseEntity.ok(orderService.getAllOrders(page, size, direction, sortBy));
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws IdNotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

}
