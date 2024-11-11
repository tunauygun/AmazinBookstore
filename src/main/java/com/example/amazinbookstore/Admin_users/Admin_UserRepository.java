package com.example.amazinbookstore.Admin_users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Admin_UserRepository extends JpaRepository<Admin_User, Long> {
    // You can define custom queries here if needed
}

