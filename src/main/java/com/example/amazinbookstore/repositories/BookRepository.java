package com.example.amazinbookstore.repositories;

import com.example.amazinbookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // You can define custom queries here if needed
}

