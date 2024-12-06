package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.repositories.BookRepository;
import com.example.amazinbookstore.repositories.PurchaseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest

class BookControllerTest {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private BookRepository adminBookRepository;

    @Autowired
    private BookController adminBookController;

    @BeforeEach
    void setUp() {
        purchaseRepository.deleteAll();
        adminBookRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        adminBookRepository.deleteAll();
    }

    @Test
    void getAllBooks() {
        List<String> authorNames = Arrays.asList("Author One", "Author Two");
        Book book = new Book(authorNames, "Test Author", "Fiction", 12, 100, "Ben", 3, "My boy Ben handsome", "https://example.com", 2023);
        adminBookRepository.save(book);

        // Act
        List<Book> result = adminBookController.getAllBooks();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("My boy Ben handsome", result.get(0).getTitle());
    }

    @Test
    void getBookById() {
        List<String> authorNames = Arrays.asList("Author One", "Author Two");
        Book book = new Book(authorNames, "Test Author", "Fiction", 12, 100, "Ben", 3, "My boy Ben handsome", "https://example.com", 2023);
        Book savedBook = adminBookRepository.save(book);
        Long bookId = savedBook.getId();

        ResponseEntity<Book> response = adminBookController.getBookById(bookId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookId, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("My boy Ben handsome", response.getBody().getTitle());
    }

    @Test
    void createBook() {
        List<String> authorNames = Arrays.asList("Author One", "Author Two");
        Book book = new Book(authorNames, "Test Author", "Fiction", 12, 100, "Ben", 3, "My boy Ben handsome", "https://example.com", 2023);
        Book result = adminBookController.createBook(book);

        assertNotNull(result);
        assertEquals("My boy Ben handsome", result.getTitle());
        assertEquals(1, adminBookRepository.findAll().size());
    }

    @Test
    void updateBook() {
        List<String> authorNames = Arrays.asList("Author One", "Author Two");
        Book book = new Book(authorNames, "Test Author", "Fiction", 12, 100, "Ben", 3, "My boy Ben handsome", "https://example.com", 2023);
        Book savedBook = adminBookRepository.save(book);
        Long bookId = savedBook.getId();

        Book updatedDetails = new Book(List.of("Ben Li"), "Updated Author", "Fiction", 33, 300, "Ben company", 99, "Ben not handsome", "https://example.com/updated", 2099);
        ResponseEntity<Book> response = adminBookController.updateBook(bookId, updatedDetails);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ben not handsome", Objects.requireNonNull(response.getBody()).getTitle());
        assertEquals(List.of("Ben Li"), response.getBody().getAuthorNames());
    }

    @Test
    void deleteBook() {
        List<String> authorNames = Arrays.asList("Author One", "Author Two");
        Book book = new Book(authorNames, "Test Author", "Fiction", 12, 100, "Ben", 3, "My boy Ben handsome", "https://example.com", 2023);
        Book savedBook = adminBookRepository.save(book);
        Long bookId = savedBook.getId();
        ResponseEntity<Void> response = adminBookController.deleteBook(bookId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(0, adminBookRepository.findAll().size());
    }
}