package com.example.amazinbookstore.Admin_books;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Admin_BookRepository extends JpaRepository<Admin_Book, Long> {
    // You can define custom queries here if needed
}

