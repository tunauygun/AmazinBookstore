package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.User;
import com.example.amazinbookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/amazinBookstore")
public class UserViewController {

    @Autowired
    private UserRepository UserRepository;

    public UserViewController() {
    }

    @GetMapping
    public String viewAllUsers(Model model) {
        List<User> Users = this.UserRepository.findAll();
        model.addAttribute("user", Users);
        return "Users.html";
    }
}
