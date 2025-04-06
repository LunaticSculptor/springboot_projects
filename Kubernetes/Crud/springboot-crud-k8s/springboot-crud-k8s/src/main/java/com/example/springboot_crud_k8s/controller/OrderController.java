package com.example.springboot_crud_k8s.controller;

import com.example.springboot_crud_k8s.entity.Orders;
import com.example.springboot_crud_k8s.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public Orders addOrder(@RequestBody Orders order){
        return service.addOrder(order);
    }

    @GetMapping
    public List<Orders> getOrders(){
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable int id){
        return service.getOrderById(id);
    }
}
