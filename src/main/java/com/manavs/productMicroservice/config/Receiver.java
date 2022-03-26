package com.manavs.productMicroservice.config;

import com.manavs.productMicroservice.models.response_models.ProductMessage;
import com.manavs.productMicroservice.services.ProductServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private ProductServices productServices;

    @RabbitListener(queues = ProductQueueConfig.QUEUE_NAME)
    public void consumeMessageFromQueue(ProductMessage productMessage) {
        System.out.print("Message ID:" + productMessage.getId() + "\n");
        System.out.println("Message received from queue : " + productMessage.getStatus());
        switch (productMessage.getStatus()) {
            case "UPDATE":
                productServices.updateProduct(productMessage.getProduct());
                break;
            case "PROCESS":
                productServices.addProduct(productMessage.getProduct());
                break;
            case "DELETE":
                productServices.removeProduct(productMessage.getProduct());
                break;
        }
    }

}