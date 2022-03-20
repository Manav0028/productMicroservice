package com.manavs.productMicroservice.models.db_models;

import javax.persistence.*;

@Entity
public class ItemDetailsMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemDetailId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Product product;
    private String detailDescription;
    private Double price;


    public int getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(int itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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
