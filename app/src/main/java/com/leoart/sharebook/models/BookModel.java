package com.leoart.sharebook.models;

public class BookModel {
    private int id;
    private String name;
    private String author;
    private int coverResource;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCoverResource() {
        return coverResource;
    }

    public void setCoverResource(int coverResource) {
        this.coverResource = coverResource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", coverResource=" + coverResource +
                ", status='" + status + '\'' +
                '}';
    }
}
