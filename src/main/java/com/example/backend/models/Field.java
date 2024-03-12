package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Field {
    private String name;
    @JsonProperty("start_position")
    private Integer startPosition;
    @JsonProperty("end_position")
    private Integer endPosition;
    @JsonProperty("data_type")
    private String dataType;

    public Field(String name, Integer startPosition, Integer endPosition, String dataType) {
        this.name = name;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
