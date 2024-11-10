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
    private BookRepository adminBookRepository;

    // View all books
    @GetMapping("/admin/books")
    public String viewAllBooks(Model model) {
        List<Book> adminBooks = adminBookRepository.findAll();
        model.addAttribute("books", adminBooks);
        return "Admin_Books"; // This maps to books.html
    }
}
