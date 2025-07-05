package com.example.backend.data;

public class DataItem {
    private int id;
    private String label;
    private String value;

    public DataItem() {
    }

    public DataItem(int id, String label, String value) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataItem that = (DataItem) o;
        return id == that.id && java.util.Objects.equals(label, that.label) && java.util.Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, label, value);
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", value=" + value +
                '}';
    }
}
