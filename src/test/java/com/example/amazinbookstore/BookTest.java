package com.example.amazinbookstore;

import com.example.amazinbookstore.entities.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class BookTest {
    @Test
    public void testAdminBookConstructor() {

        List<String> authorNames = Arrays.asList("Author One", "Author Two");
        String genre = "Fiction";
        String isbn = "1234567890";
        int pageCount = 350;
        double price = 29.99;
        String publisherName = "Test Publisher";
        int quantity = 10;
        String title = "Test Book";
        String url = "https://example.com";
        int publicationYear = 2023;

        Book book = new Book(authorNames, genre, isbn, pageCount, price, publisherName, quantity, title, url, publicationYear);

        assertNotNull(book.getAuthorNames());
        assertEquals(Arrays.asList("Author One", "Author Two"), book.getAuthorNames());
        assertEquals("Author One, Author Two", book.getAuthorNamesAsString());
        assertEquals("Fiction", book.getGenre());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(350, book.getPageCount());
        assertEquals(29.99, book.getPrice());
        assertEquals("$29.99", book.getPriceAsString());
        assertEquals("Test Publisher", book.getPublisherName());
        assertEquals(10, book.getQuantity());
        assertEquals("Test Book", book.getTitle());
        assertEquals("https://example.com", book.getUrl());
        assertEquals(2023, book.getPublicationYear());
    }


    @Test
    public void testAdminBookSetters() {
        Book book = new Book();

        book.setAuthorNames(Arrays.asList("Author One", "Author Two"));
        book.setGenre("Science Fiction");
        book.setIsbn("0987654321");
        book.setPageCount(400);
        book.setPrice(19.99);
        book.setPublisherName("Different Publisher");
        book.setQuantity(15);
        book.setTitle("Different Test Book");
        book.setUrl("https://example.com/different-book");
        book.setPublicationYear(2025);

        // Assert
        assertNotNull(book.getAuthorNames());
        assertEquals(Arrays.asList("Author One", "Author Two"), book.getAuthorNames());
        assertEquals("Author One, Author Two", book.getAuthorNamesAsString());
        assertEquals("Science Fiction", book.getGenre());
        assertEquals("0987654321", book.getIsbn());
        assertEquals(400, book.getPageCount());
        assertEquals(19.99, book.getPrice());
        assertEquals("$19.99", book.getPriceAsString());
        assertEquals("Different Publisher", book.getPublisherName());
        assertEquals(15, book.getQuantity());
        assertEquals("Different Test Book", book.getTitle());
        assertEquals("https://example.com/different-book", book.getUrl());
        assertEquals(2025, book.getPublicationYear());
    }
}