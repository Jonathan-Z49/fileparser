package com.example.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Document(collection = "flatdata")
public class Flatdata {
    @Id
    private String id;
    @Field("data")
    private Map<String, String> data;

    @DocumentReference
    @Field("fileMetaData")
    private Metadata metadata;

    public Flatdata() {

    }

    public Flatdata(String id, Map<String, String> data ,Metadata metadata) {
        this.id = id;
        this.data = data;
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", data=" + data +
                '}';
    }
}
