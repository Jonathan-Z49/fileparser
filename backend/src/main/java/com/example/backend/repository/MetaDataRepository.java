package com.example.backend.repository;

import com.example.backend.models.Metadata;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetaDataRepository extends MongoRepository<Metadata, String> {
    Optional<List<Metadata>> findAllByUserAndTypeOrderByCreatedAtDesc(ObjectId userId, String type);
}
