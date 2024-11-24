package com.example.amazinbookstore.repositories;

import com.example.amazinbookstore.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    // You can define custom queries here if needed
}
