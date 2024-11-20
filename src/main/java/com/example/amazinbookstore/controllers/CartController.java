package com.example.amazinbookstore.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @PostMapping
    public RedirectView addNewBookToCart(@RequestParam("bookId") String bookId) {
        // We will add the code to add the book to the cart in the next milestone

        // For this milestone, we just print the book id
        System.out.println("Book ID to be added to cart: " + bookId);
        return new RedirectView("/amazinBookstore/cart");
    }
}
