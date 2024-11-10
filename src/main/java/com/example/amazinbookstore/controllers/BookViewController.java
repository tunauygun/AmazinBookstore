package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class BookViewController {

    @Autowired
    private BookRepository bookRepository;

    // Manage all books as customer
    @GetMapping("/admin/books")
    public String manageAllBooksAsAdmin(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Admin_Books";
    }

    // View all books as customer
    @GetMapping("customer/books")
    public String viewAllBooksAsCustomer(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Customer_Books";
    }
}
