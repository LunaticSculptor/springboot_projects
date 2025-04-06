package com.example.product_service.service;

import com.example.product_service.exception.IdNotFoundException;
import com.example.product_service.model.Product;
import com.example.product_service.model.ProductResponse;
import com.example.product_service.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public Page<Product> getAllProducts(int page, int size, String direction, String sortBy) {
//        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepo.findAll(pageable);
    }

    public Product getProductById(Long id) throws IdNotFoundException {
        return productRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Product with id " + id + " not found"));
    }

    public Product addProduct(Product product) {
        if (productRepo.existsByName(product.getName())) {
            throw new IllegalArgumentException("Name must be unique");
        }
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product product) throws IdNotFoundException {
        Product existingProduct = productRepo.findById(id).orElseThrow(()-> new IdNotFoundException("Product with id " + id + " not found"));
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setStock(product.getStock());
            return productRepo.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) throws IdNotFoundException {
        if(!productRepo.existsById(id)) {
            throw new IdNotFoundException("Product with id " + id + " not found");
        }
        productRepo.deleteById(id);
    }


    @RabbitListener(queues = "${rabbitmq.queue.name.json}")
    public void reduceStock(ProductResponse response) throws IdNotFoundException {
        LOGGER.info("Received message: {}", response.toString());

        Long productId = response.getProductId();
        Integer quantity = response.getQuantity();

        Product product = new Product();
        product = productRepo.findById(productId).orElseThrow(()->new IdNotFoundException("Product with id " + productId + " not found"));

        product.setStock(product.getStock() - quantity);

        productRepo.save(product);
    }

    public Product getProductInfo(Long id) {
//        System.out.println("Product info service called");
        return productRepo.findById(id).orElse(null);
    }
}
