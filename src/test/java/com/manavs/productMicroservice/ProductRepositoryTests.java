package com.manavs.productMicroservice;

import com.manavs.productMicroservice.models.db_models.ItemDetailsMaster;
import com.manavs.productMicroservice.models.db_models.Product;
import com.manavs.productMicroservice.repository.ItemDetailsMasterRepository;
import com.manavs.productMicroservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemDetailsMasterRepository idmRepository;

    ArrayList<Product> productsList = new ArrayList<>();

    @BeforeEach
    void setup() {
        int i = 1;
        Product p;
        ItemDetailsMaster item;
        while(i <= 5) {
            p = new Product();
            p.setProductName("P" + i);
            p.setProductCategory("PC"+i);
            item = new ItemDetailsMaster();
            item.setDetailDescription("Detail Description of i " + i);
            item.setPrice(i*33.3);
            item.setProduct(p);
            p.setItemDetailsMaster(item);

            productsList.add(productRepository.save(p));

            i++;
        }
    }

    @Test
    public void testGetProductWithItemDetails() {
        Product product = productRepository.findById(productsList.get(0).getPid()).orElse(null);
        assertNotNull(product.getItemDetailsMaster());
        System.out.println(1*33.3);
        assertEquals(1*33.3,product.getItemDetailsMaster().getPrice());
    }

    @Test
    public void deleteCascade() {
        Iterable<Product> products = productRepository.findAll();
        products.forEach(product -> {
            int itemId = product.getItemDetailsMaster().getItemDetailId();
            productRepository.delete(product);
            ItemDetailsMaster item = idmRepository.findById(itemId).orElse(null);
            assertNull(item);
        });
    }

//    @AfterEach
//    public void removeData() {
//        productRepository.deleteAll();
//        idmRepository.deleteAll();
//    }
}
