package com.example.amazinbookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class NavigationViewController {

    @GetMapping("/")
    public RedirectView redirectToMainPage() {
        return new RedirectView("/amazinBookstore");
    }

    @GetMapping("/amazinBookstore")
    public String viewMainPage() {
        return "Main_Page";
    }

    @GetMapping("/amazinBookstore/admin")
    public String viewAdminPage() {
        return "Admin_Page";
    }
}