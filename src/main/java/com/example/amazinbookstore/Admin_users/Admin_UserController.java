package com.example.amazinbookstore.Admin_users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*") // Allow CORS for all origins (needed if you're accessing from another host or port)
@RestController
@RequestMapping("/api/users")
public class Admin_UserController {

    @Autowired
    private Admin_UserRepository adminUserRepository;

    // Get all users
    @GetMapping
    public List<Admin_User> getAllUsers() {
        return adminUserRepository.findAll();
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin_User> getUserById(@PathVariable("id") Long id) {
        Admin_User adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(adminUser);
    }

    // Create a new user
    @PostMapping
    public Admin_User createUser(@RequestBody Admin_User adminUser) {
        return adminUserRepository.save(adminUser);
    }

    // Update a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<Admin_User> updateUser(@PathVariable("id") Long id, @RequestBody Admin_User adminUserDetails) {
        Admin_User adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        adminUser.setName(adminUserDetails.getName());
        adminUser.setAdminStatus(adminUserDetails.getAdminStatus());

        Admin_User updatedAdminUser = adminUserRepository.save(adminUser);
        return ResponseEntity.ok(updatedAdminUser);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        Admin_User adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        adminUserRepository.delete(adminUser);
        return ResponseEntity.noContent().build();
    }
}
