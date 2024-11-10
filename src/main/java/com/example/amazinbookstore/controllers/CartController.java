package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class CartController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/cart")
    public RedirectView addNewBookToCart(@RequestParam("bookId") String bookId) {
        // We will add the code to add the book to the cart in the next milestone

        // For this milestone, we just print the book id
        System.out.println("Book ID to be added to cart: " + bookId);
        return new RedirectView("/cart");
    }
}
