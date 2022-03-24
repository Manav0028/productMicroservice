package com.manavs.productMicroservice.services;

import com.manavs.productMicroservice.models.db_models.ItemDetailsMaster;
import com.manavs.productMicroservice.models.db_models.Product;
import com.manavs.productMicroservice.models.response_models.ProductResponse;
import com.manavs.productMicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;


    public void addProduct(Product productRequest) {
        productRequest.getItemDetailsMaster().setProduct(productRequest);
        productRepository.save(productRequest);

    }

    public void updateProduct(Product productRequest) {
        productRequest.getItemDetailsMaster().setProduct(productRequest);
        productRepository.findById(productRequest.getPid()).map( product -> {
                    product.setProductName(productRequest.getProductName());
                    product.setProductCategory(productRequest.getProductCategory());
                    product.getItemDetailsMaster().setProduct(product);
                    product.getItemDetailsMaster().setPrice(productRequest.getItemDetailsMaster().getPrice());
                    product.getItemDetailsMaster().setDetailDescription(productRequest.getItemDetailsMaster().getDetailDescription());
                    return productRepository.save(product);
                }
        );
    }

    public void removeProduct(Product product) {
        productRepository.findById(product.getPid()).ifPresent(product1 -> productRepository.deleteById(product.getPid()));
    }
}
