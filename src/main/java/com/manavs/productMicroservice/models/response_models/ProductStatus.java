package com.manavs.productMicroservice.models.response_models;

import com.manavs.productMicroservice.models.db_models.Product;

public class ProductStatus {

    private int id;

    private Product product;
    private String status;
    private String message;

    public ProductStatus() {
    }

    public ProductStatus(Product product, String status, String message) {
        this.product = product;
        this.status = status;
        this.message = message;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productResponse) {
        this.product = productResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
