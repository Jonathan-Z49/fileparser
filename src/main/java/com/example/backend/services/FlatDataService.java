package com.example.backend.services;

import com.example.backend.models.Flatdata;
import com.example.backend.models.Metadata;
import com.example.backend.repository.FlatDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FlatDataService {

    private final FlatDataRepository flatDataRepository;

    @Autowired
    public FlatDataService(FlatDataRepository flatDataRepository){
        this.flatDataRepository = flatDataRepository;
    }

    public List<Flatdata> createFlatData(List<Map<String, String>> records, Metadata file){
        List<Flatdata> allRecords = new ArrayList<>();
        records.forEach(recordMap -> {
            Flatdata dataRecord = new Flatdata();
            dataRecord.setData(recordMap);
            dataRecord.setMetadata(file);
            allRecords.add(flatDataRepository.save(dataRecord));
        });

        return allRecords;
    }
}
