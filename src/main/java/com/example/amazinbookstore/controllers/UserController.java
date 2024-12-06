package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.entities.User;
import com.example.amazinbookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController() {
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        });
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return (User)this.userRepository.save(user);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User userDetails) {
        User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        });
        user.setName(userDetails.getName());
        user.setIsAdmin(userDetails.getIsAdmin());
        User updatedUser = (User)this.userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        });
        this.userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}