package com.example.backend.services;

import com.example.backend.models.Field;
import com.example.backend.models.Flatdata;
import com.example.backend.models.Metadata;
import com.example.backend.repository.MetaDataRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class FileService {

    private final MetaDataService metaDataService;
    private final FlatDataService flatDataService;

    @Autowired
    public FileService(MetaDataService metaDataService, FlatDataService flatDataService){
        this.metaDataService = metaDataService;
        this.flatDataService = flatDataService;
    }

    public List<Flatdata> parseFiles(MultipartFile specFile, MultipartFile dataFile, String userId) throws IOException {
        return parseRecords(specFile, dataFile, userId);
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

    public List<Flatdata> parseRecords(MultipartFile specFile, MultipartFile dataFile, String userId) throws IOException {
        Map<String, Field> spec = parseSpec(specFile);

        InputStream inputStream = dataFile.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<Map<String, String>> records = bufferedReader.lines().map(line -> {
            Map<String, String> parsedData = new HashMap<>();
            Set<String> fields = spec.keySet();
            for(String fieldName : fields) {
                Field field = spec.get(fieldName);
                String fieldValue = line.substring(field.getStartPosition()-1, field.getEndPosition()).trim();
                parsedData.put(fieldName, fieldValue);
            }
            return parsedData;
        }).toList();

        Metadata flatFileMetaData = metaDataService.createFileMetaData(dataFile, userId, "flat");
        Metadata specFileMetaData = metaDataService.createFileMetaData(specFile, userId, "spec");

        return flatDataService.createFlatData(records, flatFileMetaData, specFileMetaData, userId);
    }

}
