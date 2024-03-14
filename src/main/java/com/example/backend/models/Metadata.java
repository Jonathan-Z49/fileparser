package com.example.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "metadata")
public class Metadata {

    @Id
    private String id;

    @Field("Size")
    private long bytes;

    @Field("Filename")
    private String fileName;

    public Metadata() {
    }

    public Metadata(String id, long bytes, String fileName) {
        this.id = id;
        this.bytes = bytes;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
