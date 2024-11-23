package com.example.amazinbookstore;

import com.example.amazinbookstore.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {
    @Test
    public void testUserConstructor() {

        User user = new User("Test Name", true);
        Assertions.assertEquals("Test Name", user.getName());
        Assertions.assertEquals(true, user.getAdminStatus());
    }


    @Test
    public void testAdminBookSetters() {
        User user = new User();
        user.setName("Test Name");
        user.setAdminStatus(true);
        Assertions.assertEquals("Test Name", user.getName());
        Assertions.assertEquals(true, user.getAdminStatus());
    }
}