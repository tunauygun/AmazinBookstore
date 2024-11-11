package com.example.amazinbookstore.Admin_users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest

class Admin_UserControllerTest {

    @Autowired
    private Admin_UserRepository adminUserRepository;

    @Autowired
    private Admin_UserController adminUserController;
    @BeforeEach
    void setUp() {

        adminUserRepository.deleteAll();
    }


    @Test
    void getAllUsers() {
        Admin_User user = new Admin_User("Test Name", true);
        adminUserRepository.save(user);

        // Act
        List<Admin_User> result = adminUserController.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Name", result.get(0).getName());
    }

    @Test
    void getUserById() {
        Admin_User user = new Admin_User("Test Name", true);
        Admin_User savedUser = adminUserRepository.save(user);
        Long userId = savedUser.getId();

        ResponseEntity<Admin_User> response = adminUserController.getUserById(userId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userId, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Test Name", response.getBody().getName());
    }

    @Test
    void createUser() {
        Admin_User user = new Admin_User("Test Name", true);
        Admin_User result = adminUserController.createUser(user);

        assertNotNull(result);
        assertEquals("Test Name", result.getName());
        assertEquals(1, adminUserRepository.findAll().size());
    }

    @Test
    void updateUser() {
        Admin_User user = new Admin_User("Test Name", true);
        Admin_User savedUser = adminUserRepository.save(user);
        Long userId = savedUser.getId();

        Admin_User updatedDetails = new Admin_User("Updated Name", true);
        ResponseEntity<Admin_User> response = adminUserController.updateUser(userId, updatedDetails);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Name", Objects.requireNonNull(response.getBody()).getName());
        assertEquals(true, response.getBody().getAdminStatus());
    }

    @Test
    void deleteUser() {
        Admin_User user = new Admin_User("Test Name", true);
        Admin_User savedUser = adminUserRepository.save(user);
        Long userId = savedUser.getId();
        ResponseEntity<Void> response = adminUserController.deleteUser(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(0, adminUserRepository.findAll().size());
    }
}