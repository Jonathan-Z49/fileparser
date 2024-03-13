package com.example.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "records")
public class Record {
    @Id
    private String id;

    public Record(String id, String fileIdRef, List<Map<String, String>> data) {
        super();
        this.id = id;
        this.fileIdRef = fileIdRef;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileIdRef() {
        return fileIdRef;
    }

    public void setFileIdRef(String fileIdRef) {
        this.fileIdRef = fileIdRef;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

    @DBRef
    private String fileIdRef;
    private List<Map<String, String>> data;
}
