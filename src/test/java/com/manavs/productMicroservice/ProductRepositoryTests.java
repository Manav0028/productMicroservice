package com.manavs.productMicroservice;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
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
    ArrayList<ItemDetailsMaster> itemsList = new ArrayList<>();

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

//            builder code
//            p = Product.builder().productName("P" + i).productCategory("PC"+i).build();
//            productsList.add(productRepository.save(p));
//            item = ItemDetailsMaster.builder().detailDescription("Detail Description of i " + i).price(i*33.3).build();
//            itemsList.add(idmRepository.save(item));
//            i++;
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
    public void testGetItemDetailsWithProduct() {
        ItemDetailsMaster itemDetailsMaster = idmRepository.findById(1).orElse(null);
        assertNotNull(itemDetailsMaster.getProduct());
        assertEquals("PC1",itemDetailsMaster.getProduct().getProductCategory()); // error, product is null
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
