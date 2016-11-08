package com.mdfazlerabbi.diulibrary;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String category;
    private String edition;
    private String isbn;
    private int quantity;

    public Book() {
    }

    public Book(String bookName, String isbn, int quantity) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.quantity = quantity;
        this.category = "Not Available";
        this.author = "Not Available";
        this.publisher = "Not Available";
        this.edition = "Not Available";
    }

    public Book(int bookId, String bookName, String author, String publisher, String category, String edition, String isbn, int quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.edition = edition;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public Book(String bookName, String author, String publisher, String category, String edition, String isbn, int quantity) {
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.edition = edition;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
