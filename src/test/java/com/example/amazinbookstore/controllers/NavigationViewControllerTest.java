package com.example.amazinbookstore.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NavigationViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRedirectToMainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/amazinBookstore"));
    }

    @Test
    void testViewMainPage() throws Exception {
        mockMvc.perform(get("/amazinBookstore"))
                .andExpect(status().isOk())
                .andExpect(view().name("Main_Page"));
    }

    @Test
    void testViewAdminPage() throws Exception {
        mockMvc.perform(get("/amazinBookstore/admin"))
                .andExpect(status().isOk()) // Expect HTTP 200 (OK)
                .andExpect(view().name("Admin_Page"));
    }
}