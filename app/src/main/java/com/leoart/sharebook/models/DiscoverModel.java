package com.leoart.sharebook.models;

import java.util.List;

public class DiscoverModel {

    public DiscoverModel() {
    }

    public DiscoverModel(List<BookModel> books, String categoryTitle) {
        this.books = books;
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }

    private String categoryTitle;
    private List<BookModel> books;
}
