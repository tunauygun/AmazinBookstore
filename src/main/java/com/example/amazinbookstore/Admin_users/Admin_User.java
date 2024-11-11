package com.example.amazinbookstore.Admin_users;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isAdmin;

    // Constructors
    public Admin_User() {}

    public Admin_User(String name, Boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdminStatus() {
        return isAdmin;
    }

    public void setAdminStatus(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

