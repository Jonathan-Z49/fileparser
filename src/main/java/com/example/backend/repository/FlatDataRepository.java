package com.example.backend.repository;

import com.example.backend.models.Flatdata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlatDataRepository extends MongoRepository<Flatdata, String> {

}
