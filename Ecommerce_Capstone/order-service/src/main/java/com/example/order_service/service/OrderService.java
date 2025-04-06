package com.example.order_service.service;

import com.example.order_service.exception.IdNotFoundException;
import com.example.order_service.exception.InvalidOrderException;
import com.example.order_service.feign.OrderFeign;
import com.example.order_service.model.*;
import com.example.order_service.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderFeign orderFeign;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.json}")
    private String routingKeyJson;

    private final RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public OrderResponse placeOrder(OrderRequest request) throws IdNotFoundException, InvalidOrderException {

        Long productId = request.getProductId();
        Integer quantity = request.getQuantity();
//        System.out.println("response "+ request.toString());

        // fetch product details and check if quantity <= stock
//        Product product = new Product();
//        product.setProductId(1L);
//        product.setName("Laptop");
//        product.setStock(100000);
//        product.setPrice(1000.0);
//        product.setCategory(Product.Category.valueOf("ELECTRONICS"));

        Product product = orderFeign.getProductInfo(productId);
        if(product == null) {
            throw new IdNotFoundException("Product with id "+productId+" doesn't exit");
        }
        if(product.getStock() < quantity) {
            throw new InvalidOrderException("Stock not sufficient");
        }

        Order order = new Order();
        order.setQuantity(quantity);
        order.setTotalPrice(quantity*product.getPrice());
        order.setOrderDate(LocalDateTime.now());
        order.setProductId(productId);

        Order savedOrder = orderRepo.save(order);

        ProductResponse response = new ProductResponse();
        response.setProductId(productId);
        response.setQuantity(quantity);
        // send this response to product service to reduce stock
//        orderFeign.reduceStock(response);         // Using feign
        LOGGER.info("Json message sent-> {}", response);
        rabbitTemplate.convertAndSend(exchange, routingKeyJson, response);


        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(savedOrder.getOrderId());
        orderResponse.setProductId(savedOrder.getProductId());
        orderResponse.setQuantity(savedOrder.getQuantity());
        orderResponse.setTotalPrice(savedOrder.getTotalPrice());
        orderResponse.setOrderDate(savedOrder.getOrderDate());
        orderResponse.setStatus("Order Placed");
        return orderResponse;
    }

    public Page<Order> getAllOrders(int page, int size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return orderRepo.findAll(pageable);
    }

    public Order getOrderById(Long orderId) throws IdNotFoundException {
        return orderRepo.findById(orderId).orElseThrow(()->new IdNotFoundException("No such orderId"));
    }

}
