package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.entities.Cart;
import com.example.amazinbookstore.entities.CartItem;
import com.example.amazinbookstore.repositories.BookRepository;
import com.example.amazinbookstore.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public RedirectView addNewBookToCart(@RequestParam("bookId") Long bookId) {

        // TODO:
        // As the user login functionality is under development, we just get the first cart,
        // or create a new one if there is none.
        Optional<Cart> cartOptional = cartRepository.findById(1L);
        Cart cart = cartOptional.orElseGet(Cart::new);

        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()) {
            CartItem cartItem = new CartItem();
            cartItem.setBook(book.get());
            cartItem.setQuantity(1);
            cart.addCartItem(cartItem);
            cartRepository.save(cart);
        }

        return new RedirectView("/amazinBookstore/cart");
    }

    @PostMapping("{cartId}/updateQuantity/{itemId}/{action}")
    public ResponseEntity<?> updateQuantity(@PathVariable Long cartId, @PathVariable Long itemId, @PathVariable String action) {
        try {
            Optional<Cart> cartOptional = cartRepository.findById(cartId);
            if(cartOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Cart cart = cartOptional.get();

            // Perform the increment or decrement based on the action
            if ("increment".equalsIgnoreCase(action)) {
                boolean isSuccessful = cart.incrementCartItemQuantity(itemId);
                if (!isSuccessful) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Quantity cannot be increased due to available quantity.");
            } else if ("decrement".equalsIgnoreCase(action)) {
                boolean isSuccessful = cart.decrementCartItemQuantity(itemId);
                if (!isSuccessful) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Quantity cannot be decremented.");
            } else {
                return ResponseEntity.badRequest().body("Invalid action or quantity too low.");
            }

            cartRepository.save(cart);
            return ResponseEntity.ok("Quantity updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating quantity.");
        }
    }
}
