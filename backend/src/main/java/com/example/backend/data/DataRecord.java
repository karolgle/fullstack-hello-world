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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRecord that = (DataRecord) o;
        return id == that.id && value == that.value && java.util.Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, label, value);
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", value=" + value +
                '}';
    }
}
