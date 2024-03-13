package com.example.backend.services;

import com.example.backend.models.Field;
import com.example.backend.models.Record;
import com.example.backend.repository.RecordRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class FileService {

    //TODO
    // Store parsed files in DB
    // Store file metadata in DB
    private final RecordRepository recordRepository;

    @Autowired
    public FileService(RecordRepository recordRepository){
        this.recordRepository = recordRepository;
    }

    public String parseFiles(MultipartFile specFile, MultipartFile dataFile) throws IOException {
        return parseRecords(parseSpec(specFile), dataFile);
    }

    public Map<String, Field> parseSpec(MultipartFile specFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Field> map = mapper.readValue(specFile.getBytes(), new TypeReference<Map<String, Field>>() {});

        Set<String> keySet = map.keySet();
        for(String s : keySet) {
            map.get(s).setName(s);
        }
        return map;
    }

    public String parseRecords(Map<String, Field> spec, MultipartFile dataFile) throws IOException {

        InputStream inputStream = dataFile.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<Map<String, String>> records = bufferedReader.lines().map(line -> {
            Map<String, String> parsedData = new HashMap<>();
            Set<String> fields = spec.keySet();
            for(String fieldName : fields) {
                Field field = spec.get(fieldName);
                String fieldValue = line.substring(field.getStartPosition(), field.getEndPosition()+1).trim();
                parsedData.put(fieldName, fieldValue);
            }
            return parsedData;
        }).toList();

        records.forEach(pair -> {
            System.out.println(pair.keySet());
            System.out.println(pair.values());
        });

        return records.toString();
    }

}
