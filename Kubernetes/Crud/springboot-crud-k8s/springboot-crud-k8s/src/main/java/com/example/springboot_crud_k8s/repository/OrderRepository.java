package com.example.springboot_crud_k8s.repository;

import com.example.springboot_crud_k8s.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
