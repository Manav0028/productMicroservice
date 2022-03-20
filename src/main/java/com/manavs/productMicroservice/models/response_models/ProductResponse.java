package com.manavs.productMicroservice.models.response_models;

public class ProductResponse {

    private String productName;
    private String productCategory;
    private ItemDetailResponse itemDetailResponse;

    public ProductResponse(String productName, String productCategory, ItemDetailResponse itemDetailsResponse) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.itemDetailResponse = itemDetailsResponse;
    }
    public ProductResponse() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public ItemDetailResponse getItemDetailResponse() {
        return itemDetailResponse;
    }

    public void setItemDetailResponse(ItemDetailResponse itemDetailResponse) {
        this.itemDetailResponse = itemDetailResponse;
    }
}
