package com.manavs.productMicroservice.repository;

import com.manavs.productMicroservice.models.db_models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
