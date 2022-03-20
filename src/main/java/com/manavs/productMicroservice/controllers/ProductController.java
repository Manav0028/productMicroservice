package com.manavs.productMicroservice.controllers;

import com.manavs.productMicroservice.models.response_models.ItemDetailResponse;
import com.manavs.productMicroservice.models.db_models.ItemDetailsMaster;
import com.manavs.productMicroservice.models.db_models.Product;
import com.manavs.productMicroservice.models.response_models.ProductResponse;
import com.manavs.productMicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public @ResponseBody List<ProductResponse> getAllProducts() {
        List<ProductResponse> result = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            result.add(new ProductResponse(product.getProductName(), product.getProductCategory(),
                    new ItemDetailResponse(product.getItemDetailsMaster().getDetailDescription(),
                            product.getItemDetailsMaster().getPrice())));
        });
        return result;
    }

    @PostMapping("/addProduct")
    public @ResponseBody String addProductDetails(@RequestBody ProductResponse response) {
        Product product = new Product();
        ItemDetailsMaster item = new ItemDetailsMaster();

        updateProduct(response, product, item);

        return "Product Details Added.";
    }

    @PutMapping("/updateProduct/{id}")
    public @ResponseBody String updateProductItem(@RequestBody ProductResponse response, @PathVariable int id) {
        Product product = productRepository.getById(id);
        ItemDetailsMaster item = product.getItemDetailsMaster();

        updateProduct(response, product, item);

        return "Product Details Updated.";
    }

    @DeleteMapping("removeProduct/{id}")
    public @ResponseBody String removeProduct(@PathVariable int id) {
        productRepository.deleteById(id);
        return "Product Removed.";
    }

    private void updateProduct(ProductResponse response, Product product, ItemDetailsMaster item) {
        product.setProductName(response.getProductName());
        product.setProductCategory(response.getProductCategory());

        item.setProduct(product);
        item.setPrice(response.getItemDetailResponse().getPrice());
        item.setDetailDescription(response.getItemDetailResponse().getDetailDescription());

        product.setItemDetailsMaster(item);

        productRepository.save(product);
    }

}
