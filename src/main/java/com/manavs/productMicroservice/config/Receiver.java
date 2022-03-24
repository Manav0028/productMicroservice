package com.manavs.productMicroservice.config;

import com.manavs.productMicroservice.models.response_models.ProductStatus;
import com.manavs.productMicroservice.services.ProductServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private ProductServices productServices;

    @RabbitListener(queues = ProductQueueConfig.QUEUE_NAME)
    public void consumeMessageFromQueue(ProductStatus productStatus) {
        System.out.println("Message received from queue : " + productStatus.getStatus());
        switch (productStatus.getStatus()) {
            case "UPDATE":
                productServices.updateProduct(productStatus.getProduct());
                break;
            case "PROCESS":
                productServices.addProduct(productStatus.getProduct());
                break;
            case "DELETE":
                productServices.removeProduct(productStatus.getProduct());
                break;
        }
    }

}