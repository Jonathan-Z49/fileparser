package com.example.backend.services;

import com.example.backend.models.Field;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

@Service
public class FileService {

    public String readCompleteChars(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        while(reader.ready()) {
            builder.append((char)reader.read());
        }
        return builder.toString();
    }
    public String readAllBytes(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath())).intern();
    }

    public Map<String, Field> parseSpec(File specFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Field> map = mapper.readValue(specFile, new TypeReference<Map<String, Field>>() {});

        Set<String> keySet = map.keySet();
        for(String s : keySet) {
            map.get(s).setName(s);
        }

        System.out.println(map);
        return map;
    }

    public Map<Field, String> readStringFields(String data, Map<String, Field> spec) throws IOException {
        Map<Field, String> fileData = new HashMap<>();

        Set<String> fields = spec.keySet();
        for(String fieldName : fields) {
            Field field = spec.get(fieldName);
            String fieldValue = data.substring(field.getStartPosition(), field.getStartPosition()+1).trim();
            fileData.put(field, fieldValue);
            System.out.println("[" + fieldName + "][" + fieldValue + "]");
        }
        return fileData;
    }
}
