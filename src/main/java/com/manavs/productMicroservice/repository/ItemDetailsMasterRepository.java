package com.manavs.productMicroservice.repository;

import com.manavs.productMicroservice.models.db_models.ItemDetailsMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDetailsMasterRepository extends JpaRepository<ItemDetailsMaster, Integer> {
}
