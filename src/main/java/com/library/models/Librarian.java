package com.library.models;

import java.time.LocalDateTime;

public class Librarian {
    private int librarianId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDateTime lastLogin;

    // Default constructor
    public Librarian() {
    }

    // Constructor with basic fields
    public Librarian(String name, String username, String password, String email, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Constructor with all fields
    public Librarian(int librarianId, String name, String username, String password, String email, String role,
                     LocalDateTime lastLogin) {
        this.librarianId = librarianId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.lastLogin = lastLogin;
    }

    // Getters and Setters
    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}