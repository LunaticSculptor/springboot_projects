package com.example.order_service.feign;

import com.example.order_service.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PRODUCT-SERVICE", path = "/products")
public interface OrderFeign {

    @GetMapping("/info")
    Product getProductInfo(@RequestParam("id") Long id);

//    @PostMapping("/reduceStock")
//    void reduceStock(@RequestBody ProductResponse response);
}
