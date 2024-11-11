package com.example.amazinbookstore.Admin_users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view/admin/books")
public class Admin_UserViewController {

    @Autowired
    private Admin_UserRepository adminUserRepository;

    // View all books
    @GetMapping
    public String viewAllUsers(Model model) {
        List<Admin_User> adminUsers = adminUserRepository.findAll();
        model.addAttribute("books", adminUsers);
        return "Admin_Users.html"; // This maps to Admin_Users.html
    }
}
