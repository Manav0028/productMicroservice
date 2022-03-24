package com.manavs.productMicroservice.controllers;

import com.manavs.productMicroservice.config.ProductQueueConfig;
import com.manavs.productMicroservice.models.response_models.ItemDetailResponse;
import com.manavs.productMicroservice.models.db_models.ItemDetailsMaster;
import com.manavs.productMicroservice.models.db_models.Product;
import com.manavs.productMicroservice.models.response_models.ProductResponse;
import com.manavs.productMicroservice.models.response_models.ProductStatus;
import com.manavs.productMicroservice.repository.ProductRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/all")
    public @ResponseBody List<ProductResponse> getAllProducts() {
        List<ProductResponse> result = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            result.add(new ProductResponse(product.getProductName(), product.getProductCategory(),
                    new ItemDetailResponse(product.getItemDetailsMaster().getDetailDescription(),
                            product.getItemDetailsMaster().getPrice())));
        });
        return result;
    }

    @PostMapping("/addProduct")
    public @ResponseBody String addProductDetails(@RequestBody Product product) {
        ProductStatus status = new ProductStatus(product, "PROCESS", product.getProductName() + "product added successfully ");
        rabbitTemplate.convertAndSend(ProductQueueConfig.EXCHANGE_NAME, ProductQueueConfig.ROUTING_KEY, status);
        return "Product Add Request Received!";

    }

    @PutMapping("/updateProduct/{id}")
    public @ResponseBody String updateProductItem(@RequestBody Product productRequest, @PathVariable int id) {
        productRequest.setPid(id);
        ProductStatus status = new ProductStatus(productRequest,"UPDATE", "Update Request Received" );
        rabbitTemplate.convertAndSend(ProductQueueConfig.EXCHANGE_NAME, ProductQueueConfig.ROUTING_KEY, status);

        return "Update Request Received.";
    }

    @DeleteMapping("removeProduct/{id}")
    public @ResponseBody String removeProduct(@PathVariable int id) {
        Product product = new Product();
        product.setPid(id);
        ProductStatus status = new ProductStatus(product,"DELETE", "Delete Request Received" );
        rabbitTemplate.convertAndSend(ProductQueueConfig.EXCHANGE_NAME, ProductQueueConfig.ROUTING_KEY, status);

        return "Delete Request Received.";
    }

}
