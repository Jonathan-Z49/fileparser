package com.example.backend.services;

import com.example.backend.models.Flatdata;
import com.example.backend.models.Metadata;
import com.example.backend.repository.FlatDataRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FlatDataService {

    private final FlatDataRepository flatDataRepository;

    @Autowired
    public FlatDataService(FlatDataRepository flatDataRepository){
        this.flatDataRepository = flatDataRepository;
    }

    public List<Flatdata> createFlatData(List<Map<String, String>> records, Metadata flatFile, Metadata specFile, String userId){
        List<Flatdata> allRecords = new ArrayList<>();
        records.forEach(recordMap -> {
            Flatdata dataRecord = new Flatdata(recordMap, flatFile, specFile, new ObjectId(userId));
            allRecords.add(flatDataRepository.save(dataRecord));
        });
        return allRecords;
    }

    public List<Flatdata> findFlatDataByFile(ObjectId id, ObjectId userId){
        return flatDataRepository.findAllByFileId(id, userId).orElse(null);
    }
}
