package com.example.amazinbookstore;

import com.example.amazinbookstore.controllers.UserController;
import com.example.amazinbookstore.entities.User;
import com.example.amazinbookstore.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

class UserControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;

    UserControllerTest() {
    }

    @BeforeEach
    void setUp() {
        this.userRepository.deleteAll();
    }

    @Test
    void getAllUsers() {
        User user = new User("Test Name", true);
        this.userRepository.save(user);
        List<User> result = this.userController.getAllUsers();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Test Name", ((User)result.get(0)).getName());
    }

    @Test
    void getUserById() {
        User user = new User("Test Name", true);
        User savedUser = (User)this.userRepository.save(user);
        Long userId = savedUser.getId();
        ResponseEntity<User> response = this.userController.getUserById(userId);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userId, ((User)Objects.requireNonNull((User)response.getBody())).getId());
        Assertions.assertEquals("Test Name", ((User)response.getBody()).getName());
    }

    @Test
    void createUser() {
        User user = new User("Test Name", true);
        User result = this.userController.createUser(user);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Test Name", result.getName());
        Assertions.assertEquals(1, this.userRepository.findAll().size());
    }

    @Test
    void updateUser() {
        User user = new User("Test Name", true);
        User savedUser = (User)this.userRepository.save(user);
        Long userId = savedUser.getId();
        User updatedDetails = new User("Updated Name", true);
        ResponseEntity<User> response = this.userController.updateUser(userId, updatedDetails);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Updated Name", ((User)Objects.requireNonNull((User)response.getBody())).getName());
        Assertions.assertEquals(true, ((User)response.getBody()).getAdminStatus());
    }

    @Test
    void deleteUser() {
        User user = new User("Test Name", true);
        User savedUser = (User)this.userRepository.save(user);
        Long userId = savedUser.getId();
        ResponseEntity<Void> response = this.userController.deleteUser(userId);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertEquals(0, this.userRepository.findAll().size());
    }
}