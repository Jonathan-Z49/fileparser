package com.example.backend.models;

import org.bson.types.ObjectId;
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
    @Field("fileFlatData")
    private Metadata flatFile;

    @DocumentReference
    @Field("fileSpecData")
    private Metadata specFile;

    @Field("user")
    private ObjectId user;

    public Flatdata() {

    }

    public Flatdata(String id, Map<String, String> data ,Metadata flatFile, Metadata specFile) {
        this.id = id;
        this.data = data;
        this.flatFile = flatFile;
        this.specFile = specFile;
    }

    public Flatdata(Map<String, String> data ,Metadata flatFile, Metadata specFile,ObjectId userId) {
        this.data = data;
        this.flatFile = flatFile;
        this.specFile = specFile;
        this.user = userId;
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

    public Metadata getFlatFile() {
        return flatFile;
    }

    public void setFlatFile(Metadata flatFile) {
        this.flatFile = flatFile;
    }

    public Metadata getSpecFile() {
        return specFile;
    }

    public void setSpecFile(Metadata specFile) {
        this.specFile = specFile;
    }

    public String getUser() {
        return user.toString();
    }

    public void setUser(ObjectId user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", data=" + data +
                '}';
    }
}
