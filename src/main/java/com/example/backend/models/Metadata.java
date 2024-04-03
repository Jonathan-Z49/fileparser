package com.example.backend.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "metadata")
public class Metadata {

    @Id
    private String id;

    @Field("Size")
    private long bytes;

    @Field("Filename")
    private String fileName;

    @Field("user")
    private ObjectId user;

    @Field("fileType")
    private String type;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;

    public Metadata() {
    }

    public Metadata(String id, long bytes, String fileName) {
        this.id = id;
        this.bytes = bytes;
        this.fileName = fileName;
    }

    public Metadata(long bytes, String fileName, ObjectId user, String type) {
        this.bytes = bytes;
        this.fileName = fileName;
        this.user = user;
        this.type = type;
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

    public String getUser() {
        return user.toString();
    }

    public void setUser(ObjectId user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
