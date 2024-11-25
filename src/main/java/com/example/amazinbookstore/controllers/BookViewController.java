package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.Book;
import com.example.amazinbookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/amazinBookstore")
public class BookViewController {

    @Autowired
    private BookRepository bookRepository;

    // Manage all books as admin
    @GetMapping("/admin/books")
    public String manageAllBooksAsAdmin(@RequestParam(value = "sort", required = false) String sort, Model model) {
        List<Book> books;
        if ("alphabetical".equals(sort)) {
            books = bookRepository.findAll(Sort.by("title").ascending());
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        return "Admin_Books";
    }



    @GetMapping("/customer/books")
    public String viewAllBooksAsCustomer(@RequestParam(value = "sort", required = false) String sort, Model model) {
        List<Book> books;
        if ("alphabetical".equals(sort)) {
            books = bookRepository.findAll(Sort.by("title").ascending());
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        return "Customer_Books";
    }

    // View all recommended books as customer
    @GetMapping("/customer/recommended_books")
    public String viewAllRecBooksAsCustomer(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "Recommended_Books";
    }
}
