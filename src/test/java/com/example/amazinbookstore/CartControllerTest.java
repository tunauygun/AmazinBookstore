package com.example.amazinbookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddNewBookToCart() throws Exception {
        // Perform a POST request to /cart with the book id
        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .param("bookId", "12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));
    }

}