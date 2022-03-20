package com.manavs.productMicroservice.repository;

import com.manavs.productMicroservice.models.db_models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
