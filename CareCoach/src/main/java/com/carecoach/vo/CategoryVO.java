package com.carecoach.vo;


public class CategoryVO {

    private String name;
    private int writeAt;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWriteAt() {
        return writeAt;
    }

    public void setWriteAt(int writeAt) {
        this.writeAt = writeAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "name='" + name + '\'' +
                ", writeAt=" + writeAt +
                ", id=" + id +
                '}';
    }
}
