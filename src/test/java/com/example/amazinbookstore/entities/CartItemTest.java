package com.example.amazinbookstore.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {
    @Test
    void testCartItemConstructorAndGetters() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book1");

        CartItem cartItem = new CartItem(1L, book, 5);

        assertEquals(1L, cartItem.getId());
        assertEquals(book, cartItem.getBook());
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    void testCartItemSetters() {
        CartItem cartItem = new CartItem();
        Book book = new Book();
        book.setId(2L);
        book.setTitle("BookTitle");

        cartItem.setId(1L);
        cartItem.setBook(book);
        cartItem.setQuantity(10);

        // Assert
        assertEquals(1L, cartItem.getId());
        assertEquals(book, cartItem.getBook());
        assertEquals(10, cartItem.getQuantity());
    }
}