package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Cart;
import com.example.amazinbookstore.repositories.BookRepository;
import com.example.amazinbookstore.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/amazinBookstore")
public class CartViewController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    // TODO:
    // As the user login functionality is under development, we just get the first cart,
    // or create a new one if there is none.
    @GetMapping("/cart")
    public String viewCartPage(Model model) {
        Optional<Cart> cartOptional = cartRepository.findById(1L);
        Cart cart = cartOptional.orElseGet(Cart::new);

        model.addAttribute("cart", cart);
        return "Cart_Page";
    }
}
