package com.example.amazinbookstore.Admin_books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*") // Allow CORS for all origins (needed if you're accessing from another host or port)
@RestController
@RequestMapping("/api/books")
public class Admin_BookController {

    @Autowired
    private Admin_BookRepository adminBookRepository;

    // Get all books
    @GetMapping
    public List<Admin_Book> getAllBooks() {
        return adminBookRepository.findAll();
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin_Book> getBookById(@PathVariable("id") Long id) {
        Admin_Book adminBook = adminBookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return ResponseEntity.ok(adminBook);
    }

    // Create a new book
    @PostMapping
    public Admin_Book createBook(@RequestBody Admin_Book adminBook) {
        return adminBookRepository.save(adminBook);
    }

    // Update a book by ID
    @PutMapping("/{id}")
    public ResponseEntity<Admin_Book> updateBook(@PathVariable("id") Long id, @RequestBody Admin_Book adminBookDetails) {
        Admin_Book adminBook = adminBookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        adminBook.setAuthorNames(adminBookDetails.getAuthorNames());
        adminBook.setGenre(adminBookDetails.getGenre());
        adminBook.setIsbn(adminBookDetails.getIsbn());
        adminBook.setPageCount(adminBookDetails.getPageCount());
        adminBook.setPrice(adminBookDetails.getPrice());
        adminBook.setPublisherName(adminBookDetails.getPublisherName());
        adminBook.setQuantity(adminBookDetails.getQuantity());
        adminBook.setTitle(adminBookDetails.getTitle());
        adminBook.setUrl(adminBookDetails.getUrl());
        adminBook.setPublicationYear(adminBookDetails.getPublicationYear());

        Admin_Book updatedAdminBook = adminBookRepository.save(adminBook);
        return ResponseEntity.ok(updatedAdminBook);
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        Admin_Book adminBook = adminBookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        adminBookRepository.delete(adminBook);
        return ResponseEntity.noContent().build();
    }
}
