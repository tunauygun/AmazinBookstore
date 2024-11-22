package com.example.amazinbookstore.repositories;

import com.example.amazinbookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom queries here if needed
}

