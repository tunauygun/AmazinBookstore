package com.example.amazinbookstore.Admin_users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Admin_UserTest {
    @Test
    public void testAdminUserConstructor() {

        Admin_User user = new Admin_User("Test Name", true);

        assertEquals("Test Name", user.getName());
        assertEquals(true, user.getAdminStatus());
    }


    @Test
    public void testAdminUserSetters() {
        Admin_User user = new Admin_User();

        user.setName("Test Name");
        user.setAdminStatus(true);

        assertEquals("Test Name", user.getName());
        assertEquals(true, user.getAdminStatus());
    }
}