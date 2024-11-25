package com.example.amazinbookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/amazinBookstore")
public class UserViewController {


    // Manage all books as admin
    @GetMapping("/admin/users")
    public String manageAllUsersAsAdmin() {
        return "Admin_Users";
    }
}
