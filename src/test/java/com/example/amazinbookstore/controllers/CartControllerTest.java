package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.entities.Cart;
import com.example.amazinbookstore.repositories.BookRepository;
import com.example.amazinbookstore.repositories.CartRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeTestClass
    void setUp() {
        cartRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {

        cartRepository.deleteAll();
    }

    @Test
    void testAddNewBookToCart_BookExists() throws Exception {
        // Arrange
        Long bookId = 1L;
        Book mockBook = new Book();
        mockBook.setId(bookId);
        mockBook.setQuantity(5);
        mockBook.setTitle("Test Book");

        Cart mockCart = new Cart();
        when(cartRepository.findById(1L)).thenReturn(Optional.of(mockCart));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        // Act
        mockMvc.perform(post("/api/cart")
                        .param("bookId", bookId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/amazinBookstore/cart"));

        // Confirm the cart item is added to the cart
        ArgumentCaptor<Cart> cartCaptor = ArgumentCaptor.forClass(Cart.class);
        verify(cartRepository, times(1)).save(cartCaptor.capture());

        Cart savedCart = cartCaptor.getValue();
        assertEquals(1, savedCart.getCartItems().size());
        assertEquals(mockBook, savedCart.getCartItems().get(0).getBook());
        assertEquals(1, savedCart.getCartItems().get(0).getQuantity());
    }

    @Test
    void testAddNewBookToCart_BookDoesNotExist() throws Exception {
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/cart")
                        .param("bookId", bookId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/amazinBookstore/cart"));

        verify(cartRepository, never()).save(any(Cart.class));
    }

    @Test
    void testAddNewBookToCart_CartDoesNotExist() throws Exception {
        // Arrange
        Long bookId = 1L;
        Book mockBook = new Book();
        mockBook.setId(bookId);
        mockBook.setTitle("Test Book");
        mockBook.setQuantity(5);

        when(cartRepository.findById(1L)).thenReturn(Optional.empty());
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        // Act
        mockMvc.perform(post("/api/cart")
                        .param("bookId", bookId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/amazinBookstore/cart"));

        // Confirm the cart item is added to the cart
        ArgumentCaptor<Cart> cartCaptor = ArgumentCaptor.forClass(Cart.class);
        verify(cartRepository, times(1)).save(cartCaptor.capture());

        Cart savedCart = cartCaptor.getValue();
        assertEquals(1, savedCart.getCartItems().size());
        assertEquals(mockBook, savedCart.getCartItems().get(0).getBook());
        assertEquals(1, savedCart.getCartItems().get(0).getQuantity());
    }

    @Test
    void testUpdateQuantity_CartNotFound() throws Exception {
        Long cartId = 1L;
        Long itemId = 100L;

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/cart/{cartId}/updateQuantity/{itemId}/{action}", cartId, itemId, "increment"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateQuantity_IncrementSuccess() throws Exception {
        Long cartId = 1L;
        Long itemId = 100L;

        Cart mockCart = mock(Cart.class);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(mockCart));
        when(mockCart.incrementCartItemQuantity(itemId)).thenReturn(true);

        mockMvc.perform(post("/api/cart/{cartId}/updateQuantity/{itemId}/{action}", cartId, itemId, "increment"))
                .andExpect(status().isOk())
                .andExpect(content().string("Quantity updated successfully."));

        verify(cartRepository, times(1)).save(mockCart);
    }

    @Test
    void testUpdateQuantity_IncrementFailure() throws Exception {
        Long cartId = 1L;
        Long itemId = 100L;

        Cart mockCart = mock(Cart.class);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(mockCart));
        when(mockCart.incrementCartItemQuantity(itemId)).thenReturn(false);

        mockMvc.perform(post("/api/cart/{cartId}/updateQuantity/{itemId}/{action}", cartId, itemId, "increment"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Quantity cannot be increased due to available quantity."));
    }

    @Test
    void testUpdateQuantity_DecrementSuccess() throws Exception {
        Long cartId = 1L;
        Long itemId = 100L;

        Cart mockCart = mock(Cart.class);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(mockCart));
        when(mockCart.decrementCartItemQuantity(itemId)).thenReturn(true);

        mockMvc.perform(post("/api/cart/{cartId}/updateQuantity/{itemId}/{action}", cartId, itemId, "decrement"))
                .andExpect(status().isOk())
                .andExpect(content().string("Quantity updated successfully."));

        verify(cartRepository, times(1)).save(mockCart);
    }

    @Test
    void testUpdateQuantity_DecrementFailure() throws Exception {
        Long cartId = 1L;
        Long itemId = 100L;

        Cart mockCart = mock(Cart.class);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(mockCart));
        when(mockCart.decrementCartItemQuantity(itemId)).thenReturn(false);

        mockMvc.perform(post("/api/cart/{cartId}/updateQuantity/{itemId}/{action}", cartId, itemId, "decrement"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Quantity cannot be decremented."));
    }

    @Test
    void testUpdateQuantity_InvalidAction() throws Exception {
        Long cartId = 1L;
        Long itemId = 100L;

        Cart mockCart = mock(Cart.class);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(mockCart));

        mockMvc.perform(post("/api/cart/{cartId}/updateQuantity/{itemId}/{action}", cartId, itemId, "invalidAction"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid action or quantity too low."));
    }
}
