package com.example.second;

public class Book {
    private String name;
    private String author;

    public Book() {}

    public Book(String bName, String bAuthor) {
        this.name = bName;
        this.author = bAuthor;
    }

    public String getbName() {
        return name;
    }

    public String getbAuthor() {
        return author;
    }

    public void setbName(String name) {
        this.name = name;
    }

    public void setbAuthor(String author) {
        this.author = author;
    }
}
