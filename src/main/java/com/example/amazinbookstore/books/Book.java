package com.example.amazinbookstore.books;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> authorNames = new ArrayList<>();


    private String genre;
    private String isbn;
    private int pageCount;
    private double price;
    private String publisherName;
    private int quantity;
    private String title;
    private String url;  // URL for the book cover or more information
    private int publicationYear;

    // Constructors
    public Book() {}

    public Book(List<String> authorNames, String genre, String isbn, int pageCount, double price, String publisherName, int quantity, String title, String url, int publicationYear) {
        this.authorNames = authorNames;
        this.genre = genre;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.price = price;
        this.publisherName = publisherName;
        this.quantity = quantity;
        this.title = title;
        this.url = url;
        this.publicationYear = publicationYear;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getAuthorNames() {
        return new ArrayList<>(authorNames);
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int year) {
        this.publicationYear = year;
    }
}

