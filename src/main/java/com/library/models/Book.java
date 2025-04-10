package com.library.models;

import java.time.LocalDateTime;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String category;
    private int quantity;
    private int available;
    private LocalDateTime addedDate;

    // Default constructor
    public Book() {
    }

    // Constructor with all fields except ID and added date
    public Book(String title, String author, String publisher, String isbn, String category, int quantity) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.category = category;
        this.quantity = quantity;
        this.available = quantity;
    }

    // Constructor with all fields
    public Book(int bookId, String title, String author, String publisher, String isbn, String category, int quantity,
                int available, LocalDateTime addedDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.category = category;
        this.quantity = quantity;
        this.available = available;
        this.addedDate = addedDate;
    }

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}