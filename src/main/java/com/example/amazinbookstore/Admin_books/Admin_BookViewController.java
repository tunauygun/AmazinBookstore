package com.example.amazinbookstore.Admin_books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view/admin/books")
public class Admin_BookViewController {

    @Autowired
    private Admin_BookRepository adminBookRepository;

    // View all books
    @GetMapping
    public String viewAllBooks(Model model) {
        List<Admin_Book> adminBooks = adminBookRepository.findAll();
        model.addAttribute("books", adminBooks);
        return "Admin_Books.html"; // This maps to books.html
    }
}
