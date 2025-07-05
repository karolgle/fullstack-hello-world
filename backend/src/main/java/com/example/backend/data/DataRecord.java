package com.example.backend.data;

public class DataRecord {
    private int id;
    private String label;
    private int value;

    public DataRecord() {
    }

    public DataRecord(int id, String label, int value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
