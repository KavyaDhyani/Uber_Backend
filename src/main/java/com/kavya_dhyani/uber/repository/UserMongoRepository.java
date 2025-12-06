package com.kavya_dhyani.uber.repository;

import com.kavya_dhyani.uber.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
