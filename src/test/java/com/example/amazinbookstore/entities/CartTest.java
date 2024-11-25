package com.example.amazinbookstore.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private CartItem cartItem1;
    private CartItem cartItem2;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        // Set up the books to add to the cart
        book1 = new Book();
        book1.setId(1L);
        book1.setQuantity(1);
        book2 = new Book();
        book2.setQuantity(2);
        book2.setId(2L);


        // Set up the cart items
        cartItem1 = new CartItem();
        cartItem1.setId(1L);
        cartItem1.setQuantity(1);
        cartItem1.setBook(book1);

        cartItem2 = new CartItem();
        cartItem2.setId(2L);
        cartItem2.setQuantity(1);
        cartItem2.setBook(book2);

        // Set up the cart
        cart = new Cart();
        cart.addCartItem(cartItem1);
        cart.addCartItem(cartItem2);
    }

    @Test
    void testAddingCartItem_ExistingBook() {
        // Add existing book within the quantity limit
        cart.addCartItem(cartItem2);
        assertNotNull(cart);
        assertEquals(2, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertSame(cartItem2, cart.getCartItems().get(1));
        assertEquals(1, cartItem1.getQuantity());
        assertEquals(2, cartItem2.getQuantity());


        // Add existing book exceeding the quantity limit
        cart.addCartItem(cartItem2);
        assertNotNull(cart);
        assertEquals(2, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertSame(cartItem2, cart.getCartItems().get(1));
        assertEquals(1, cartItem1.getQuantity());
        assertEquals(2, cartItem2.getQuantity());
    }

    @Test
    void testAddingCartItem_NewBook() {
        // Create a new book
        Book book3 = new Book();
        book3.setQuantity(1);
        book3.setId(3L);

        // Set up the cart item
        CartItem cartItem3 = new CartItem();
        cartItem3.setId(3L);
        cartItem3.setQuantity(1);
        cartItem3.setBook(book3);

        cart.addCartItem(cartItem3);

        assertNotNull(cart);
        assertEquals(3, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertSame(cartItem2, cart.getCartItems().get(1));
        assertSame(cartItem3, cart.getCartItems().get(2));
        assertEquals(1, cartItem1.getQuantity());
        assertEquals(1, cartItem2.getQuantity());
        assertEquals(1, cartItem3.getQuantity());
    }

    @Test
    void testIncrementCartItemQuantity() {
        // Attempting to increment non-existing item
        boolean isSuccessful1 = cart.incrementCartItemQuantity(3L);
        assertFalse(isSuccessful1);
        assertEquals(2, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertSame(cartItem2, cart.getCartItems().get(1));
        assertEquals(1, cartItem1.getQuantity());
        assertEquals(1, cartItem2.getQuantity());

        // Attempting to increment an existing item within quantity limit
        boolean isSuccessful2 = cart.incrementCartItemQuantity(2L);
        assertTrue(isSuccessful2);
        assertEquals(2, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertSame(cartItem2, cart.getCartItems().get(1));
        assertEquals(1, cartItem1.getQuantity());
        assertEquals(2, cartItem2.getQuantity());

        // Attempting to increment an existing item exceeding quantity limit
        boolean isSuccessful3 = cart.incrementCartItemQuantity(2L);
        assertFalse(isSuccessful3);
        assertEquals(2, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertSame(cartItem2, cart.getCartItems().get(1));
        assertEquals(1, cartItem1.getQuantity());
        assertEquals(2, cartItem2.getQuantity());
    }

    @Test
    void testDecrementCartItemQuantity() {
        // Update cart item quantity manually for testing
        cartItem2.setQuantity(2);

        // Attempting to decrement non-existing item
        boolean isSuccessful1 = cart.decrementCartItemQuantity(3L);
        assertFalse(isSuccessful1);
        assertEquals(2, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertSame(cartItem2, cart.getCartItems().get(1));
        assertEquals(1, cartItem1.getQuantity());
        assertEquals(2, cartItem2.getQuantity());

        // Attempting to increment an existing item with >1 quantity
        boolean isSuccessful2 = cart.decrementCartItemQuantity(2L);
        assertTrue(isSuccessful2);
        assertEquals(2, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertSame(cartItem2, cart.getCartItems().get(1));
        assertEquals(1, cartItem1.getQuantity());
        assertEquals(1, cartItem2.getQuantity());

        // Attempting to increment an existing item with >1 quantity
        boolean isSuccessful3 = cart.decrementCartItemQuantity(2L);
        assertTrue(isSuccessful3);
        assertEquals(1, cart.getCartItems().size());
        assertSame(cartItem1, cart.getCartItems().get(0));
        assertEquals(1, cartItem1.getQuantity());
    }
}