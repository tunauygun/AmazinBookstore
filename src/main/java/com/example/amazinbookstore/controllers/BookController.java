package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*") // Allow CORS for all origins (needed if you're accessing from another host or port)
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return ResponseEntity.ok(book);
    }

    // Create a new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Update a book by ID
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        book.setAuthorNames(bookDetails.getAuthorNames());
        book.setGenre(bookDetails.getGenre());
        book.setIsbn(bookDetails.getIsbn());
        book.setPageCount(bookDetails.getPageCount());
        book.setPrice(bookDetails.getPrice());
        book.setPublisherName(bookDetails.getPublisherName());
        book.setQuantity(bookDetails.getQuantity());
        book.setTitle(bookDetails.getTitle());
        book.setUrl(bookDetails.getUrl());
        book.setPublicationYear(bookDetails.getPublicationYear());

        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }
}
