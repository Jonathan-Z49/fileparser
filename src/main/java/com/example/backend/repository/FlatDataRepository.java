package com.example.backend.repository;

import com.example.backend.models.Flatdata;
import com.example.backend.models.Metadata;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlatDataRepository extends MongoRepository<Flatdata, String> {

    @Query("{ $or: [ { 'fileFlatData': ?0 }, { 'fileSpecData': ?0 } ], 'user': ?1 }")
    Optional<List<Flatdata>> findAllByFileId(ObjectId metadataId, ObjectId user);

}
