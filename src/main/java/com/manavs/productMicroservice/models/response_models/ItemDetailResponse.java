package com.manavs.productMicroservice.models.response_models;

public class ItemDetailResponse {

    private String detailDescription;
    private Double price;

    public ItemDetailResponse(String detailDescription, Double price) {
        this.detailDescription = detailDescription;
        this.price = price;
    }

    public ItemDetailResponse() {}

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
