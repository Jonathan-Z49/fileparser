package com.example.backend.repository;

import com.example.backend.models.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDataRepository extends MongoRepository<Metadata, String> {

}
