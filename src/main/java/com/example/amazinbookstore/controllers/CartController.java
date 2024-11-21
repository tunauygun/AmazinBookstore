package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.entities.Cart;
import com.example.amazinbookstore.entities.CartItem;
import com.example.amazinbookstore.repositories.BookRepository;
import com.example.amazinbookstore.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            System.out.println("Book ID to be added to cart: " + bookId);
        }

        return new RedirectView("/amazinBookstore/cart");
    }
}
