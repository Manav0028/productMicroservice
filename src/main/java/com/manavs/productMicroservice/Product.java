package com.manavs.productMicroservice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    private String productName;
    private String productCategory;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private ItemDetailsMaster itemDetailsMaster;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public ItemDetailsMaster getItemDetailsMaster() {
        return itemDetailsMaster;
    }

    public void setItemDetailsMaster(ItemDetailsMaster itemDetailsMaster) {
        this.itemDetailsMaster = itemDetailsMaster;
    }
}
