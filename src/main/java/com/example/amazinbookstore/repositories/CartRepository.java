package com.example.amazinbookstore.repositories;

import com.example.amazinbookstore.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // You can define custom queries here if needed
}

