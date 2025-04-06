package com.example.springboot_crud_k8s.service;

import com.example.springboot_crud_k8s.entity.Orders;
import com.example.springboot_crud_k8s.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Orders addOrder(Orders order){
        return repository.save(order);
    }

    public List<Orders> getOrders(){
        return repository.findAll();
    }

    public Orders getOrderById(int id){
        return repository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid id : "+id));
    }
}
