package com.example.backend.repository;

import com.example.backend.models.UserRoles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRoles, String> {
    Optional<UserRoles> findByName(String name);
}
