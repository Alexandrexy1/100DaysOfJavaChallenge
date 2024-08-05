package com.example.account.entities.enums;

public enum UserRole {
    USER("user");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
