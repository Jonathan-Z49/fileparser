package com.example.backend.repository;

import com.example.backend.models.UserRoles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRoleRepository extends MongoRepository<UserRoles, String> {
    Optional<UserRoles> findByName(String name);
}
