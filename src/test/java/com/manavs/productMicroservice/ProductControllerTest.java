package com.manavs.productMicroservice;

import com.manavs.productMicroservice.controllers.ProductController;
import com.manavs.productMicroservice.models.db_models.ItemDetailsMaster;
import com.manavs.productMicroservice.models.db_models.Product;
import com.manavs.productMicroservice.models.response_models.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController controller;

    private Product product = new Product();
    private ItemDetailsMaster item = new ItemDetailsMaster();
    private int pid = 999;

    @Test
    public void addProduct() {
        product = new Product();
        product.setProductName("Product Test");
        product.setPid(pid);
        product.setProductCategory("Category Test");
        item = new ItemDetailsMaster();
        product.setItemDetailsMaster(item);
        item.setPrice(22.33);
        item.setDetailDescription("Item Detail Description Test");
        String output = controller.addProductDetails(product);
        assertEquals("Product Add Request Received.", output);
    }

    @Test
    public void updateProduct() {
        product = new Product();
        product.setProductName("Product Update Test");
        product.setProductCategory("Category Update Test");
        item = new ItemDetailsMaster();
        product.setItemDetailsMaster(item);
        item.setPrice(44.11);
        item.setDetailDescription("Item Detail Description Update Test");
        String output = controller.updateProductItem(product, pid);
        assertEquals("Update Request Received.", output);
    }

    @Test
    public void getAll() {
        List<ProductResponse> responseList = controller.getAllProducts();
        assertNotNull(responseList);
    }

    @Test
    public void deleteProduct() {
        String output = controller.removeProduct(pid);
        assertEquals("Delete Request Received.", output);
    }
}
